//
//  ViewController.swift
//  PaymentApp
//
//  Created by testbot on 8/17/15.
//  Copyright (c) 2015 testbot. All rights reserved.
//

import UIKit
import Parse

class ViewController: UIViewController, UIViewControllerTransitioningDelegate {

    var utils = Utils()
    
    @IBOutlet weak var usernameLabel: UILabel!
    
    @IBAction func logout(sender: UIButton) {
        PFUser.logOutInBackgroundWithBlock({
            (error) -> Void in
            if (error != nil) {
                self.utils.showAlert("Error", message: error!.localizedDescription, delegate: self)
            } else {
                let appDomain = NSBundle.mainBundle().bundleIdentifier
                let startUpViewController = self.storyboard!.instantiateViewControllerWithIdentifier(Constants.ViewController.Startup) as! StartUpViewController
                let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
                var navController = UINavigationController(rootViewController: startUpViewController)
                navController.transitioningDelegate = self
                self.presentViewController(navController, animated: true, completion: nil)
            }
        })
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(true)        
        var authUser:PFUser = PFUser.currentUser()!
        self.usernameLabel.text = authUser.objectForKey("name") as? String
    }
}

