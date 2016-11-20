//
//  FallingEmojiBehavior.swift
//  WhatTheFood
//
//  Created by Florian Bienefelt on 20.11.16.
//  Copyright © 2016 Florian Bienefelt. All rights reserved.
//

import UIKit

class FallingEmojiBehavior: UIDynamicBehavior {
    
    let gravity = UIGravityBehavior()
    
    fileprivate let collider: UICollisionBehavior = {
        let collider = UICollisionBehavior()
        collider.translatesReferenceBoundsIntoBoundary = true
        return collider
    }()
    
    fileprivate let itemBehavior: UIDynamicItemBehavior = {
        let dib = UIDynamicItemBehavior()
//        var defaults = UserDefaults.standard
//        
//        if (defaults.object(forKey: "rotation") != nil) {
//            dib.allowsRotation = defaults.bool(forKey: "rotation")
//        } else {
//            dib.allowsRotation = true
//        }
//        
//        if (defaults.object(forKey: "elasticity") != nil) {
//            dib.elasticity = CGFloat(defaults.float(forKey: "elasticity"))
//        } else {
//            dib.elasticity = 0.75
//        }
        
        dib.elasticity = 0.75
        dib.allowsRotation = true
        
        return dib
    }()
    
    func addBarrier(_ path: UIBezierPath, named name: String) {
        collider.removeBoundary(withIdentifier: name as NSCopying)
        collider.addBoundary(withIdentifier: name as NSCopying, for: path)
    }
    
    override init() {
        super.init()
        
        addChildBehavior(gravity)
        addChildBehavior(collider)
        addChildBehavior(itemBehavior)
    }
    
    func addItem(_ item: UIDynamicItem) {
        gravity.addItem(item)
        collider.addItem(item)
        itemBehavior.addItem(item)
    }
    
    func removeItem(_ item: UIDynamicItem) {
        gravity.removeItem(item)
        collider.removeItem(item)
        itemBehavior.removeItem(item)
    }
}
