//
//  ViewController.swift
//  PaymentApp
//
//  Created by testbot on 8/17/15.
//  Copyright (c) 2015 testbot. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var usernameLabel: UILabel!
    
    
    @IBAction func logout(sender: UIButton) {
        let appDomain = NSBundle.mainBundle().bundleIdentifier
        NSUserDefaults.standardUserDefaults().removePersistentDomainForName(appDomain!)
        let startUpViewController = self.storyboard!.instantiateViewControllerWithIdentifier(Constants.ViewController.Startup) as! StartUpViewController
        let appDelegate = UIApplication.sharedApplication().delegate as! AppDelegate
        var navController = UINavigationController(rootViewController: startUpViewController)
        appDelegate.window?.rootViewController = navController
        appDelegate.window?.makeKeyAndVisible()
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
        
        let userInfo = NSUserDefaults.standardUserDefaults()
        let userLoggedIn = userInfo.integerForKey(Constants.UserDefaults.LoggedIn) as Int
        if(userLoggedIn != 1) {
        } else {
            if let username = userInfo.valueForKey(Constants.UserDefaults.Name) as? NSString {
                self.usernameLabel.text = username as String
            }
        }
    }
}

