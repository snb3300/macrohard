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
        
        if (usernameTxt == "" || passwordTxt == "") {
            self.showAlert("Unable to Sign In!!!", message: "Username and Password are mandatory", delegate: self)
        } else {
            let credentials = [
                "username" : usernameTxt,
                "password" : passwordTxt
            ]
            println(credentials)
            Alamofire.request(.GET, "http://localhost:5000/login", parameters: credentials).responseJSON {
                (request, response, data, error) in
                if(error != nil) {
                    println("Error: \(error)")
                    self.showAlert("Connection Error", message: error!.localizedDescription, delegate: self)
                } else {
                    var jsonResponse = JSON(data!)
                    if (jsonResponse["Success"]) {
                        var userInfo:NSUserDefaults = NSUserDefaults.standardUserDefaults()
                        userInfo.setObject(jsonResponse["Name"].string, forKey: "USERNAME")
                        userInfo.setInteger(1, forKey: "LOGGED_IN")
                        userInfo.synchronize()
                        self.dismissViewControllerAnimated(true, completion: nil)
                    } else {
                        self.showAlert("Sign in failed", message: "Incorrect Credentials", delegate: self)
                    }
                }
            }
        }
    }
    
    @IBAction func gotoSignUp(sender: UIButton) {
        self.performSegueWithIdentifier("goto_signup", sender: self)
    }
    
    func showAlert(title:String, message:String, delegate:LoginViewController) {
        var alertView:UIAlertView = UIAlertView()
        alertView.title = title
        alertView.message = message
        alertView.delegate = delegate
        alertView.addButtonWithTitle("OK")
        alertView.show()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
}
