//
//  CommonMethods.swift
//  PaymentApp
//
//  Created by testbot on 8/23/15.
//  Copyright (c) 2015 testbot. All rights reserved.
//

import Foundation
import UIKit
import QuartzCore

func animation() -> CAKeyframeAnimation {
    let shake = CAKeyframeAnimation(keyPath : "transform")
    let low = NSValue( CATransform3D: CATransform3DMakeTranslation(-5, 0, 0))
    let high = NSValue( CATransform3D: CATransform3DMakeTranslation(5, 0, 0))
    shake.values = [low, high]
    shake.autoreverses = true
    shake.repeatCount = 2
    shake.duration = 7/100
    return shake
}

func showAlert(title:String, message:String, delegate:UIViewController) {
    var alertView:UIAlertView = UIAlertView()
    alertView.title = title
    alertView.message = message
    alertView.delegate = delegate
    alertView.addButtonWithTitle("OK")
    alertView.show()
}

func toggleTextField(textField:UITextField) {
    if (textField.text == "") {
        textField.layer.borderColor = UIColor.redColor().CGColor
        textField.layer.addAnimation(animation(), forKey: nil)
    } else {
        textField.layer.borderColor = UIColor.blackColor().CGColor
    }
}
