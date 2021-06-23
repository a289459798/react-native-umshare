require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "RNUMShare"
  s.version      = package["version"]
  s.summary      = package['description']
  s.author       = package['author']
  s.homepage     = package['homepage']
  s.license      = package['license']
  s.platforms    = { :ios => "9.0" }
  s.source       = { :git => "https://github.com/a289459798/react-native-umshare.git", :tag => "v#{s.version}" }
  s.source_files  = "ios/*.{h,m}"
  s.dependency "React"
  s.dependency "UMCommon", "7.3.0"
    s.dependency "UMDevice", "2.0.1"
    s.dependency "UMShare/UI", "6.10.2"
    s.dependency 'UMShare/Social/WeChat', "6.10.2"
    s.dependency 'UMShare/Social/QQ', "6.10.2"
    s.dependency 'UMShare/Social/ReducedSina', "6.10.2"
end
