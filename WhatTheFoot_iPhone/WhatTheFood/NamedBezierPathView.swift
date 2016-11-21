//
//  NamedBezierPathView.swift
//  WhatTheFood
//
//  Created by Florian Bienefelt on 20.11.16.
//  Copyright © 2016 Florian Bienefelt. All rights reserved.
//

import UIKit

class NamedBezierPathsView: UIView {
    
    var bezierPaths = [String:UIBezierPath]() { didSet { setNeedsDisplay() }}
    
    override func draw(_ rect: CGRect) {
        for (_, path) in bezierPaths {
            path.stroke()
        }
    }
    
    
}
