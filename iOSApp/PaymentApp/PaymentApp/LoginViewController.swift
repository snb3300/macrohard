//
//  LoginViewController.swift
//  PaymentApp
//
//  Created by testbot on 8/18/15.
//  Copyright (c) 2015 testbot. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON


class LoginViewController: UIViewController {

    var utils = Utils()
    
    @IBOutlet weak var email: UITextField!
    
    @IBOutlet weak var password: UITextField!
    
    @IBOutlet weak var logIn: UIButton!
    
    @IBAction func signInTap(sender: UIButton) {
        var emailTxt:NSString = email.text
        var passwordTxt:NSString = password.text
        
        if (emailTxt != "" && passwordTxt != ""){
            let credentials = [
                Constants.UserDetails.Email : emailTxt,
                Constants.UserDetails.Password : passwordTxt
            ]
            Alamofire.request(.GET, "http://localhost:5000/login", parameters: credentials).responseJSON {
                (request, response, data, error) in
                if(error != nil) {
                    println("Error: \(error)")
                    self.utils.showAlert("Connection Error", message: error!.localizedDescription, delegate: self)
                } else {
                    var jsonResponse = JSON(data!)
                    if (jsonResponse["Success"]) {
                        var userInfo:NSUserDefaults = NSUserDefaults.standardUserDefaults()
                        userInfo.setObject(jsonResponse["Name"].string, forKey: Constants.UserDefaults.Name)
                        userInfo.setInteger(1, forKey: Constants.UserDefaults.LoggedIn)
                        userInfo.synchronize()
                        var dashBoardViewController = self.storyboard!.instantiateViewControllerWithIdentifier(Constants.ViewController.Dashboard) as! ViewController
                        let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
                        appDelegate.window?.rootViewController = dashBoardViewController
                        appDelegate.window?.makeKeyAndVisible()
//                        self.dismissViewControllerAnimated(true, completion: nil)
                    } else {
                        self.utils.showAlert("Sign in failed", message: "Incorrect Credentials", delegate: self)
                        self.password.text = ""
                    }
                }
            }
        }
    }
    
    @IBAction func textFieldDidChange(sender: AnyObject) {
        logIn.enabled = self.utils.verifyEmail(email.text) && self.utils.verifyPassword(password.text)
        if(logIn.enabled) {
            logIn.alpha = 1
        } else {
            logIn.alpha = 0.5
        }
    }
    
    override func viewDidLoad() {
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        super.viewDidLoad()
//        email.layer.borderWidth = 1.0
//        password.layer.borderWidth = 1.0
        logIn.enabled = false
        logIn.alpha = 0.5
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
