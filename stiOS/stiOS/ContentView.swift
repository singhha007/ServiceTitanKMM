import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var showObject = ShowObject()
    
    init() {
        showObject.getItems()
    }
    
    var body: some View {
        Text(ShowManager().provideBuild())
        Text(ShowManager().provideCurrentDate())
        Spacer()
        List {
            ForEach(showObject.shows) { item in
                PricebookItemView(item: item)
            }
        }
    }
}

struct PricebookItemView: View {
    var item: ShowItemIdentificable? = nil
    
    var body: some View {
        HStack(spacing: 16) {
            if #available(iOS 14.0, *) {
                RemoteImage(url: "https://image.tmdb.org/t/p/w500/" + (item?.show.backdropPath)!)
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
                Text(item?.show.name ?? "").font(.headline)
                Text(item?.show.overview ?? "").font(.footnote)
            }
        }
        .padding(4)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
