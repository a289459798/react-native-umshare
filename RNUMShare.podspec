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
  s.dependency "UMCCommon", "7.1.1"
  s.dependency "UMCShare/UI", "6.9.10"
  s.dependency 'UMCShare/Social/ReducedWeChat', "6.9.10"
  s.dependency 'UMCShare/Social/ReducedQQ', "6.9.10"
  s.dependency 'UMCShare/Social/ReducedSina', "6.9.10"
end
