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
import Parse


class RegistrationViewController: UIViewController {

    var utils = Utils()
    
    @IBOutlet weak var name: UITextField!
    
    @IBOutlet weak var email: UITextField!
    
    @IBOutlet weak var password: UITextField!
    
    @IBOutlet weak var signUp: UIButton!
    
    @IBAction func signUpTap(sender: UIButton) {
        var user: PFUser = PFUser()
        user.username = email.text as String
        user.password = password.text as String
        user.email = email.text as String
        user.setObject(name.text as String, forKey: "name")
        user.signUpInBackgroundWithBlock({
            (succeeded: Bool, error:NSError?) -> Void in
            if(succeeded) {
                println("Sign up Successful")
                self.utils.gotoDashboard(self.name.text)
            } else {
                self.utils.showAlert("Error", message: error!.localizedDescription, delegate: self)
            }
        })
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
