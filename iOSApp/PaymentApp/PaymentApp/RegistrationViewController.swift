//
//  RegistrationViewController.swift
//  PaymentApp
//
//  Created by testbot on 8/18/15.
//  Copyright (c) 2015 testbot. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON


class RegistrationViewController: UIViewController {

    var utils = Utils()
    
    @IBOutlet weak var name: UITextField!
    
    @IBOutlet weak var email: UITextField!
    
    @IBOutlet weak var password: UITextField!
    
    @IBOutlet weak var signUp: UIButton!
    
    @IBAction func signUpTap(sender: UIButton) {
        let userDetails = [
            Constants.UserDetails.Name: name.text,
            Constants.UserDetails.Email: email.text,
            Constants.UserDetails.Password: password.text
        ]
        Alamofire.request(.POST, "http://localhost:5000/register", parameters: userDetails).responseJSON {
            (request, response, data, error) in
            if(error != nil) {
                println("Error: \(error)")
                self.utils.showAlert("Connection Error", message: error!.localizedDescription, delegate: self)
            } else {
                var jsonResponse = JSON(data!)
                if (jsonResponse["Success"]) {
                    self.dismissViewControllerAnimated(true, completion: nil)
                } else {
                    self.utils.showAlert("Sign up failed", message: jsonResponse["Error"].string!, delegate: self)
                }
            }
        }
    }
    
    @IBAction func textFieldDidChange(sender: AnyObject) {
        signUp.enabled = !self.name.text.isEmpty && self.utils.verifyEmail(email.text) && self.utils.verifyPassword(password.text)
        if(signUp.enabled) {
            signUp.alpha = 1
        } else {
            signUp.alpha = 0.5
        }
    }

    override func viewDidLoad() {
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        super.viewDidLoad()
//        name.layer.borderWidth = 1.0
//        email.layer.borderWidth = 1.0
//        password.layer.borderWidth = 1.0
        signUp.enabled = false
        signUp.alpha = 0.5
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
