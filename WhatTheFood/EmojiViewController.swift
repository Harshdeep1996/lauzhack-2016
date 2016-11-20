//
//  EmojiViewController.swift
//  WhatTheFood
//
//  Created by Florian Bienefelt on 20.11.16.
//  Copyright © 2016 Florian Bienefelt. All rights reserved.
//

import UIKit
import Foundation

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
    
    

    
    var closeButton: UIButton!
    var gameView: EmojiView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        gameView = EmojiView()
        gameView.frame = CGRect(x: 0, y: 0, width: view.bounds.width, height: view.bounds.height)
        gameView.backgroundColor = UIColor(red: 0.5, green: 0.5, blue: 0.5, alpha: 0.0)
        
        
        closeButton = UIButton(frame: CGRect(x: 0, y: 0, width: view.bounds.width, height: 80))
        closeButton.backgroundColor = UIColor.black
        closeButton.titleLabel?.font = UIFont(name: "Trebuchet MS", size: 25)
        closeButton.titleLabel?.textColor = UIColor.white
        closeButton.titleLabel?.text = "Close"

        
        closeButton.addTarget(self, action: #selector(buttonAction), for: .touchUpInside)
        
        view.addSubview(gameView)
        view.addSubview(closeButton)
        
//        gameView.addDrop()
//        gameView.addDrop()
//        gameView.addDrop()
        gameView.realGravity = true
    }
    
    func buttonAction(sender: UIButton!) {
        self.dismiss(animated: true)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        gameView.animating = true
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        gameView.animating = false
    }
    
    
    func addDropz(newScan: (Double, Double, Double, Double)) {
        let (carbs, sugars, fats, proteins) = newScan
        
        let totalAmount = carbs + sugars + fats + proteins
        
        let carbDrops = Int(carbs)
        let sugarDrops = Int(sugars)
        let fatDrops = Int(fats)
        let proteinDrops = Int(proteins)
        
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
        for _ in 0...proteinDrops {
            gameView.addDrop(index: 3)
        }
        
    }
    
}
