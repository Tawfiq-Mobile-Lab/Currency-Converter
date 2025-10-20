import SwiftUI

struct CardContainer<Content: View>: View {
    let content: () -> Content
    init(@ViewBuilder content: @escaping () -> Content) { self.content = content }
    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            content()
        }
        .padding(16)
        .background(
            RoundedRectangle(cornerRadius: 16, style: .continuous)
                .fill(Color.white)
                .shadow(color: .black.opacity(0.12), radius: 8, x: 0, y: 2)
        )
    }
}

struct CurrencyPicker: View {
    let title: String
    @Binding var selected: Currency

    var body: some View {
        Menu {
            ForEach(Currency.allCases) { c in
                Button(action: { selected = c }) {
                    HStack { Text("\(c.symbol)  \(c.rawValue)") }
                }
            }
        } label: {
            HStack {
                Text(selected.rawValue).foregroundStyle(.primary)
                Spacer()
                Image(systemName: "chevron.down")
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
            }
            .padding(.horizontal, 12).padding(.vertical, 10)
            .overlay(
                RoundedRectangle(cornerRadius: 8)
                    .stroke(Color.gray.opacity(0.3), lineWidth: 1)
            )
            .accessibilityLabel(Text(title))
        }
    }
}

struct ContentView: View {
    @StateObject var vm = ConverterViewModel()

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                Text("Convert").font(.title3)
                Text("Any Currency").font(.largeTitle).bold()

                CardContainer {
                    CurrencyPicker(title: "From Currency", selected: $vm.from)
                    TextField("Amount", text: $vm.input)
                        .keyboardType(.decimalPad)
                        .textFieldStyle(.roundedBorder)
                    Text(vm.rateTextTop).font(.footnote).foregroundStyle(.secondary)
                }

                HStack { Spacer()
                    Button(action: vm.swapCurrencies) {
                        Image(systemName: "arrow.up.arrow.down")
                            .foregroundStyle(.white)
                            .padding(12)
                            .background(Circle().fill(AppColors.primary))
                    }
                    Spacer()
                }

                CardContainer {
                    CurrencyPicker(title: "To Currency", selected: $vm.to)
                    Text(vm.outputFormatted).font(.system(size: 32, weight: .bold, design: .default))
                    Text(vm.rateTextBottom).font(.footnote).foregroundStyle(.secondary)
                }
            }
            .padding(20)
        }
        .background(AppColors.surface.ignoresSafeArea())
    }
}

#Preview {
    ContentView()
}
