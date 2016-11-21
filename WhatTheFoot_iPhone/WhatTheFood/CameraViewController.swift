//
//  CameraViewController.swift
//  WhatTheFood
//
//  Created by Florian Bienefelt on 20.11.16.
//  Copyright © 2016 Florian Bienefelt. All rights reserved.
//

import UIKit
import BarcodeScanner

class CameraViewController: BarcodeScannerController {

    
    @IBOutlet weak var ContainerView: UIView!
    
    var controller : EmojiViewController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        view.layoutSubviews()
//        
//        ContainerView.backgroundColor = UIColor(red: 0.5, green: 0.5, blue: 0.5, alpha: 1.0)
//        
//        view.addSubview(ContainerView)
//        view.bringSubview(toFront: ContainerView)
        
        let testFrame = CGRect(x: 0, y: 0, width: view.bounds.width, height: view.bounds.height) //(0,200,320,200)
//        
//        var testView : UIView = UIView(frame: testFrame)
//        testView.backgroundColor = UIColor(red: 0.5, green: 0.5, blue: 0.5, alpha: 1.0)
//        testView.alpha=0.5
        
        controller = EmojiViewController()
        
        self.addChildViewController(controller)
        
        view.addSubview(controller.view)
        
        controller.view.frame = testFrame
        
        // Do any additional setup after loading the view.
    }
    
    func addDropz(newScan: (Double, Double, Double, Double)) {
        DispatchQueue.main.async(){
            self.controller.addDropz(newScan: newScan)
        }
    }

}
