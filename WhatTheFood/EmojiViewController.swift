//
//  EmojiViewController.swift
//  WhatTheFood
//
//  Created by Florian Bienefelt on 20.11.16.
//  Copyright © 2016 Florian Bienefelt. All rights reserved.
//

import UIKit

class EmojiViewController: UIViewController {
    
    
//    @IBOutlet weak var gameView: EmojiView! {
//        didSet {
////            gameView.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(addDrop(_:))))
////            gameView.addGestureRecognizer(UIPanGestureRecognizer(target: gameView, action: #selector(EmojiView.grabDrop(_:))))
//            
//            gameView.addDrop()
//            gameView.addDrop()
//            gameView.addDrop()
//            gameView.realGravity = true
//        }
//    }
    
//    func addDrop(_ recognizer: UITapGestureRecognizer) {
//        if recognizer.state == .ended {
//            gameView.addDrop()
//        }
//    }
    
    
    @IBAction func CloseButton(_ sender: Any) {
    }
    
    
    var gameView: EmojiView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        gameView = EmojiView()
        
        gameView.frame = CGRect(x: 0, y: 0, width: view.bounds.width, height: view.bounds.height)
        
        gameView.backgroundColor = UIColor(red: 0.5, green: 0.5, blue: 0.5, alpha: 0.0)
        
        
        view.addSubview(gameView)
        
//        gameView.addDrop()
//        gameView.addDrop()
//        gameView.addDrop()
        gameView.realGravity = true
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        gameView.animating = true
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        gameView.animating = false
    }
    
    
    func addDropz(newScan: (Double, Double, Double)) {
        let (carbs, sugars, fats) = newScan
        
        let totalAmount = carbs + sugars + fats
        
        let carbDrops = Int(carbs)
        let sugarDrops = Int(sugars)
        let fatDrops = Int(fats)
        
        let carbColor = UIColor.brown
        let sugarColor = UIColor.white
        let fatColor = UIColor.yellow
        
        for _ in 0...carbDrops {
            gameView.addDrop(index: 0)
        }
        for _ in 0...sugarDrops {
            gameView.addDrop(index: 1)
        }
        for _ in 0...fatDrops {
            gameView.addDrop(index: 2)
        }
        
    }
    
}
