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

    @IBOutlet weak var username: UITextField!
    
    
    @IBOutlet weak var password: UITextField!
    
    @IBAction func signInTap(sender: UIButton) {
        var usernameTxt:NSString = username.text
        var passwordTxt:NSString = password.text
        
        toggleTextField(username)
        toggleTextField(password)
        
        if (usernameTxt != "" && passwordTxt != ""){
            let credentials = [
                "username" : usernameTxt,
                "password" : passwordTxt
            ]
            Alamofire.request(.GET, "http://localhost:5000/login", parameters: credentials).responseJSON {
                (request, response, data, error) in
                if(error != nil) {
                    println("Error: \(error)")
                    showAlert("Connection Error", error!.localizedDescription, self)
                } else {
                    var jsonResponse = JSON(data!)
                    if (jsonResponse["Success"]) {
                        var userInfo:NSUserDefaults = NSUserDefaults.standardUserDefaults()
                        userInfo.setObject(jsonResponse["Name"].string, forKey: "USERNAME")
                        userInfo.setInteger(1, forKey: "LOGGED_IN")
                        userInfo.synchronize()
                        self.dismissViewControllerAnimated(true, completion: nil)
                    } else {
                        showAlert("Sign in failed", "Incorrect Credentials", self)
                    }
                }
            }
        }
    }
    
//    override func viewDidAppear(animated: Bool) {
//        self.navigationController?.setNavigationBarHidden(false, animated: true)
//        super.viewDidAppear(animated)
//    }
    
    override func viewDidLoad() {
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        super.viewDidLoad()
        username.layer.borderWidth = 1.0
        password.layer.borderWidth = 1.0
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
