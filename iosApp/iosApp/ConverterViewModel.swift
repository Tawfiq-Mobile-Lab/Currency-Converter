import Foundation
import Combine

final class ConverterViewModel: ObservableObject {

    @Published var from: Currency = .EUR
    @Published var to: Currency = .USD
    @Published var input: String = "120.00"
    @Published private(set) var outputFormatted: String = ""
    @Published private(set) var rateTextTop: String = ""
    @Published private(set) var rateTextBottom: String = ""

    private let repo = ConversionRepository(provider: StaticRateProvider())
    private var cancellables = Set<AnyCancellable>()

    init() {
        Publishers.CombineLatest3($from, $to, $input)
            .sink { [weak self] f, t, input in
                self?.recompute(from: f, to: t, input: input)
            }
            .store(in: &cancellables)
        recompute(from: from, to: to, input: input)
    }

    func swapCurrencies() {
        let f = from; from = to; to = f
    }

    private func recompute(from: Currency, to: Currency, input: String) {
        let amount = Double(input.replacingOccurrences(of: ",", with: ".")) ?? 0.0
        let res = repo.convert(amount: amount, from: from, to: to)
        outputFormatted = formatAmount(res.amount, currency: to)
        rateTextTop = "1 \(from.rawValue) = \(String(format: \"%.2f\", res.rate)) \(to.rawValue)"
        let inverse = res.rate == 0 ? 0 : 1.0 / res.rate
        rateTextBottom = "1 \(to.rawValue) = \(String(format: \"%.2f\", inverse)) \(from.rawValue)"
    }
}

func formatAmount(_ value: Double, currency: Currency) -> String {
    let rounded = (value * 100).rounded() / 100.0
    switch currency {
    case .USD: return "$\(rounded)"
    case .EUR: return "€\(rounded)"
    case .GBP: return "£\(rounded)"
    case .JPY, .CNY: return "¥\(rounded)"
    case .INR: return "₹\(rounded)"
    case .CHF: return "CHF \(rounded)"
    case .CAD: return "C$\(rounded)"
    case .AUD: return "A$\(rounded)"
    case .MAD: return "MAD \(rounded)"
    }
}
