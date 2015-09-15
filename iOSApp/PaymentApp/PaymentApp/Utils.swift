//
//  Utils.swift
//  PaymentApp
//
//  Created by testbot on 9/7/15.
//  Copyright (c) 2015 testbot. All rights reserved.
//

import Foundation
import UIKit
import QuartzCore

class Utils {
    
    let MIN_PASSWORD_LENGTH = 6
    
//    func animation() -> CAKeyframeAnimation {
//        let shake = CAKeyframeAnimation(keyPath : "transform")
//        let low = NSValue( CATransform3D: CATransform3DMakeTranslation(-5, 0, 0))
//        let high = NSValue( CATransform3D: CATransform3DMakeTranslation(5, 0, 0))
//        shake.values = [low, high]
//        shake.autoreverses = true
//        shake.repeatCount = 2
//        shake.duration = 7/100
//        return shake
//    }
    
    func verifyEmail(email:String) -> Bool {
        let emailRegEx = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"
        let emailTest = NSPredicate(format: "SELF MATCHES %@", emailRegEx)
        return emailTest.evaluateWithObject(email)
    }
    
    func verifyPassword(password:NSString) -> Bool {
        return password.length >= MIN_PASSWORD_LENGTH
    }
    
    func showAlert(title:String, message:String, delegate:UIViewController) -> Void {
        var alertView:UIAlertView = UIAlertView()
        alertView.title = title
        alertView.message = message
        alertView.delegate = delegate
        alertView.addButtonWithTitle("OK")
        alertView.show()
    }
    
//    func toggleTextField(textField:UITextField) -> Void {
//        if (textField.text == "") {
//            textField.layer.borderColor = UIColor.redColor().CGColor
//            textField.layer.addAnimation(animation(), forKey: nil)
//        } else {
//            textField.layer.borderColor = UIColor.blackColor().CGColor
//        }
//    }
    
    func gotoDashboard(name: String) {
        let mainStoryBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
        var userInfo:NSUserDefaults = NSUserDefaults.standardUserDefaults()
        userInfo.setObject(name, forKey: Constants.UserDefaults.Name)
        userInfo.setInteger(1, forKey: Constants.UserDefaults.LoggedIn)
        userInfo.synchronize()
        var dashBoardViewController = mainStoryBoard.instantiateViewControllerWithIdentifier(Constants.ViewController.Dashboard) as! ViewController
        let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
        UIView.transitionWithView(appDelegate.window!, duration: 0.5, options: UIViewAnimationOptions.TransitionFlipFromLeft, animations: {appDelegate.window?.rootViewController = dashBoardViewController}, completion: nil)
    }
    
}