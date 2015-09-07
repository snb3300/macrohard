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

    
    
    @IBOutlet weak var name: UITextField!
    
    
    @IBOutlet weak var email: UITextField!
    
    @IBOutlet weak var password: UITextField!
    
    @IBAction func signUpTap(sender: UIButton) {
        let nameTxt = name.text
        let emailTxt = email.text
        let passwordTxt = password.text
        
        toggleTextField(name)
        toggleTextField(email)
        toggleTextField(password)

//        if(nameTxt != "" && emailTxt != "" && passwordTxt != "") {
//            if(passwordTxt != confirmPasswordTxt) {
//                confirmPassword.text = "";
//                toggleTextField(confirmPassword)
//            } else {
//                let userDetails = [
//                    "name": nameTxt,
//                    "email": emailTxt,
//                    "password": passwordTxt
//                ]
//                Alamofire.request(.POST, "http://localhost:5000/register", parameters: userDetails).responseJSON {
//                        (request, response, data, error) in
//                    if(error != nil) {
//                        println("Error: \(error)")
//                        showAlert("Connection Error", error!.localizedDescription, self)
//                    } else {
//                        var jsonResponse = JSON(data!)
//                        if (jsonResponse["Success"]) {
//                            self.dismissViewControllerAnimated(true, completion: nil)
//                        } else {
//                            showAlert("Sign up failed", jsonResponse["Error"].string!, self)
//                        }
//                    }
//                }
//            }
//        }
    }
    
    @IBAction func gotoLogin(sender: UIButton) {
        self.dismissViewControllerAnimated(true, completion: nil)
    }
    
//    override func viewDidAppear(animated: Bool) {
//        self.navigationController?.setNavigationBarHidden(false, animated: true)
//        super.viewDidAppear(animated)
//    }
    
    override func viewDidLoad() {
        self.navigationController?.setNavigationBarHidden(false, animated: true)
        super.viewDidLoad()
        name.layer.borderWidth = 1.0
        email.layer.borderWidth = 1.0
        password.layer.borderWidth = 1.0
        // Do any additional setup after loading the view.
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
