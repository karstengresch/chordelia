# -*- mode: ruby -*-
# # vi: set ft=ruby :

require 'fileutils'

Vagrant.require_version ">= 1.7.0"

$instance_name_prefix = "core"
$update_channel = "alpha"
$enable_serial_logging = false
$vm_gui = false
$vm_memory = 4096
$vm_cpus = 2
$shared_folders = {}
# $forwarded_ports = {}

def vm_gui
  $vb_gui.nil? ? $vm_gui : $vb_gui
end

def vm_memory
  $vb_memory.nil? ? $vm_memory : $vb_memory
end

def vm_cpus
  $vb_cpus.nil? ? $vm_cpus : $vb_cpus
end

Vagrant.configure("2") do |config|
  # always use Vagrants insecure key
  config.ssh.insert_key = false

  config.vm.box = "coreos-%s" % $update_channel
  config.vm.box_version = ">= 308.0.1"
  config.vm.box_url = "http://%s.release.core-os.net/amd64-usr/current/coreos_production_vagrant.json" % $update_channel

  ["vmware_fusion", "vmware_workstation"].each do |vmware|
    config.vm.provider vmware do |v, override|
      override.vm.box_url = "http://%s.release.core-os.net/amd64-usr/current/coreos_production_vagrant_vmware_fusion.json" % $update_channel
    end
  end

  # config.vm.provider :virtualbox do |v|
    #  On VirtualBox, we don't have guest additions or a functional vboxsf
    #  in CoreOS, so tell Vagrant that so it can be smarter.
  #  v.check_guest_additions = false
  #  v.functional_vboxsf     = false
  # end

  # plugin conflict
  if Vagrant.has_plugin?("vagrant-vbguest") then
    config.vbguest.auto_update = false
  end


  i = 1
  config.vm.define vm_name = 'quintett'
  config.vm.hostname = vm_name

  if $enable_serial_logging
    logdir = File.join(File.dirname(__FILE__), "log")
    FileUtils.mkdir_p(logdir)

    serialFile = File.join(logdir, "%s-serial.txt" % vm_name)
    FileUtils.touch(serialFile)

    ["vmware_fusion", "vmware_workstation"].each do |vmware|
      config.vm.provider vmware do |v, override|
        v.vmx["serial0.present"] = "TRUE"
        v.vmx["serial0.fileType"] = "file"
        v.vmx["serial0.fileName"] = serialFile
        v.vmx["serial0.tryNoRxLoss"] = "FALSE"
      end
    end
  end

  # if $expose_docker_tcp
  #  config.vm.network "forwarded_port", guest: 2375, host: ($expose_docker_tcp + i - 1), auto_correct: true
  # end

  config.vm.network "forwarded_port", guest: 3306, host: 14572, auto_correct: false

 # $forwarded_ports.each do |guest, host|
 #   config.vm.network "forwarded_port", guest: guest, host: host, auto_correct: true
 # end

  ["vmware_fusion", "vmware_workstation"].each do |vmware|
    config.vm.provider vmware do |v|
      v.gui = vm_gui
      v.vmx['memsize'] = vm_memory
      v.vmx['numvcpus'] = vm_cpus
    end
  end

  ip = "172.1.1.#{i+220}"
  config.vm.network :private_network, ip: ip

  config.vm.synced_folder "./", "/home/core/share/",
      id: "core",
      nfs_version: "4",
      :nfs => true,
      :mount_options => ['nolock,noatime']

  config.vm.provision :shell, :inline => "docker run -p 3306:3306 -e MYSQL_DATABASE=quintett -e MYSQL_USER=quintett -e MYSQL_ROOT_PASSWORD=nonono -d mysql:latest", :privileged => true

end