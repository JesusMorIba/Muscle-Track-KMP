import SwiftUI
import AVFoundation
import ComposeApp

class AppDelegate: NSObject, UIApplicationDelegate, PermissionRequestProtocol {

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {

        KoinIosKt.doInitKoinIos()
        PermissionBridge_iosKt.registerPermissionHandler(listener: self)
        return true
    }

    func requestCameraPermission(callback: PermissionResultCallback) {
        switch AVCaptureDevice.authorizationStatus(for: .video) {
        case .notDetermined:
            AVCaptureDevice.requestAccess(for: .video) { granted in
                DispatchQueue.main.async {
                    if granted {
                        callback.onPermissionGranted()
                        print("Camera permission granted")
                    } else {
                        callback.onPermissionDenied(isPermanentDenied: false)
                        print("Camera permission denied")
                    }
                }
            }
        case .denied:
            print("Camera access is denied")
            DispatchQueue.main.async {
                callback.onPermissionDenied(isPermanentDenied: true)
            }
        case .restricted:
            print("Camera access is restricted")
            DispatchQueue.main.async {
                callback.onPermissionDenied(isPermanentDenied: true)
            }
        case .authorized:
            print("Camera access already authorized")
            DispatchQueue.main.async {
                callback.onPermissionGranted()
            }
        @unknown default:
            fatalError("Unknown authorization status for camera")
        }
    }

    func isCameraPermissionGranted() -> Bool {
        let status = AVCaptureDevice.authorizationStatus(for: .video)
        return status == .authorized
    }
}

@main
struct iOSApp: App {

    init() {
        MainViewControllerKt.doInitKoin()
    }

    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
