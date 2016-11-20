import UIKit
import BarcodeScanner
import Gloss

class ViewController: UIViewController {
    
    var foodItems : [(String?, Double, Double, Double, Double)] = []
    
    var controller : CameraViewController!
    
    lazy var button: UIButton = {
        let button = UIButton(type: .system)
        button.backgroundColor = UIColor.black
        button.titleLabel?.font = UIFont.systemFont(ofSize: 28)
        button.setTitleColor(UIColor.white, for: UIControlState())
        button.setTitle("Scan", for: UIControlState())
        button.addTarget(self, action: #selector(buttonDidPress), for: .touchUpInside)
        
        return button
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = UIColor.white
        view.addSubview(button)
    }
    
    override func viewWillLayoutSubviews() {
        super.viewWillLayoutSubviews()
        
        button.frame.size = CGSize(width: 250, height: 80)
        button.center = view.center
    }
    
    func buttonDidPress() {
        controller = CameraViewController()
        controller.codeDelegate = self
        controller.errorDelegate = self
        controller.dismissalDelegate = self
        
        present(controller, animated: true, completion: nil)
    }
}

extension ViewController: BarcodeScannerCodeDelegate {
    
    func barcodeScanner(_ controller: BarcodeScannerController, didCaptureCode code: String, type: String) {
        print(code)
        print(type)
        getProduct(code: code)
        
        let delayTime = DispatchTime.now() + Double(Int64(6 * Double(NSEC_PER_SEC))) / Double(NSEC_PER_SEC)
        DispatchQueue.main.asyncAfter(deadline: delayTime) {
            controller.resetWithError()
        }
    }
    
    func getProduct(code: String) {
        
        let url = URL(string: "https://www.openfood.ch/api/v1/products?barcodes[]=" + code)
        
        let task = URLSession.shared.dataTask(with: url!) { data, response, error in
            guard error == nil else {
                print(error)
                return
            }
            guard let data = data else {
                print("Data is empty")
                return
            }
            
            do {
                let json = try? JSONSerialization.jsonObject(with: data) as! [String:AnyObject]
                
                var carbs: Double = 0.0
                var sugars: Double = 0.0
                var fats: Double = 0.0
                var proteins: Double = 0.0
                var name: String? = ""
                
                if let data = json?["data"] as? Array<Any> {
                    if (data.count) > 0 {
                    if let data1 = data[0] as? [String:AnyObject] {
                        if let attributes = data1["attributes"] as? [String:AnyObject] {
                            name = attributes["name"] as? String
                            if let nutrients = attributes["nutrients"] as? Array<[String:AnyObject]> {
                                
                                
                                
                                for nutrient in nutrients {
                                    let name = nutrient["name"] as! String
                                    
                                    print(name)
                                    
                                    if name == "Carbohydrates" {
                                        if let carbs1 = nutrient["per-portion"] as? String {
                                            carbs = Double(carbs1)!
                                        } else if let carbs2 = nutrient["per-hundred"] as? String {
                                            carbs = Double(carbs2)!
                                        }
                                    } else if name == "Sugars" {
                                        if let sugars1 = nutrient["per-portion"] as? String {
                                            sugars = Double(sugars1)!
                                        } else if let sugars2 = nutrient["per-hundred"] as? String {
                                            sugars = Double(sugars2)!
                                        }
                                    } else if name == "Fat" {
                                        if let fats1 = nutrient["per-portion"] as? String {
                                            fats = Double(fats1)!
                                        } else if let fats2 = nutrient["per-hundred"] as? String {
                                            fats = Double(fats2)!
                                        }
                                    } else if name == "Protein" {
                                        if let proteins1 = nutrient["per-portion"] as? String {
                                            proteins = Double(proteins1)!
                                        } else if let proteins2 = nutrient["per-hundred"] as? String {
                                            proteins = Double(proteins2)!
                                        }
                                    }
                                }
                                }
                            }
                        }
                    }
                }
                
                self.foodItems.append((name, carbs, sugars, fats, proteins))
                print(self.foodItems)
                
                self.controller.addDropz(newScan: (carbs, sugars, fats, proteins))

            } catch {
                print(error)
            }
            
        }
        
        task.resume()
    }
}

extension ViewController: BarcodeScannerErrorDelegate {
    
    func barcodeScanner(_ controller: BarcodeScannerController, didReceiveError error: Error) {
        print(error)
    }
}

extension ViewController: BarcodeScannerDismissalDelegate {
    
    func barcodeScannerDidDismiss(_ controller: BarcodeScannerController) {
        controller.dismiss(animated: true, completion: nil)
    }
}
