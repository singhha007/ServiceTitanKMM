//
//  ShowModel.swift
//  stiOS
//
//  Created by Hardeep Singh on 1/20/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

struct ShowItemIdentificable: Identifiable {
    var id = UUID()
    var show: Show
}

class ShowObject: ObservableObject {
    @Published var shows: [ShowItemIdentificable] = []
    
    func getItems() {
        ShowManager().getPopularShows(completionHandler: { ( showsList: [Show]?, error: Error?) in
            if(error == nil) {
                self.shows = showsList?.map({ ShowItemIdentificable(show: $0) }) ?? []
            } else {
                self.shows = []
            }
        })
    }
}
