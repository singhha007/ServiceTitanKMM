import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var pricebookObject = PricebookObject()
    
    init() {
        pricebookObject.getItems()
    }
    
    var body: some View {
        Text(PricebookManager().providePlatform())
        Spacer()
        List {
            ForEach(pricebookObject.pricebook) { item in
                PricebookItemView(item: item)
            }
        }
    }
}

struct PricebookItemView: View {
    var item: PricebookItemIdentifiable? = nil
    
    var body: some View {
        HStack(spacing: 16) {
            if #available(iOS 14.0, *) {
                RemoteImage(url: item?.pricebook.thumbnailUrl ?? "")
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 48, height: 48)
                    .clipShape(Circle())
            } else {
                Image(systemName: "multiply.circle")
                    .aspectRatio(contentMode: .fit)
                    .frame(width: 48, height: 48)
                    .clipShape(Circle())
            }
            
            VStack(alignment: .leading) {
                Text(item?.pricebook.name ?? "").font(.headline)
                Text(item?.pricebook.description ?? "").font(.footnote)
            }
            
            Spacer()
            
            VStack(alignment: .trailing) {
                Text(String(item?.pricebook.price ?? 0.0))
                    .font(.headline)
            }
        }
        .padding(4)
    }
}

struct PricebookItemIdentifiable: Identifiable {
    var id = UUID()
    var pricebook: PricebookItem
}

class PricebookObject: ObservableObject {
    @Published var pricebook: [PricebookItemIdentifiable] = []
    
    func getItems() {
        PricebookManager().getPricebook(page: 1, pageSize: 20, completionHandler: { pricebookResponse, error in
            if let pricebookResponse = pricebookResponse {
                self.pricebook = pricebookResponse.data.map({
                    PricebookItemIdentifiable(pricebook: $0)
                })
            } else {
                self.pricebook = []
            }
        })
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
