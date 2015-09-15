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
import Parse


class LoginViewController: UIViewController {

    var utils = Utils()
    
    @IBOutlet weak var email: UITextField!
    
    @IBOutlet weak var password: UITextField!
    
    @IBOutlet weak var logIn: UIButton!
    
    @IBAction func signInTap(sender: UIButton) {
        var emailTxt:NSString = email.text
        var passwordTxt:NSString = password.text
        
        if (emailTxt != "" && passwordTxt != "") {
            PFUser.logInWithUsernameInBackground(emailTxt as String, password: passwordTxt as String,
                block: {
                    (authUser: PFUser?, error: NSError?) -> Void in
                    if(authUser != nil) {
                        println("Login Successful")
                        self.utils.gotoDashboard(authUser?.objectForKey("name") as! String)
                    } else {
                        self.utils.showAlert("Error", message: error!.localizedDescription, delegate: self)
                    }
                }
            )
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
