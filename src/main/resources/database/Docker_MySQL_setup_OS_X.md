# About this document

## Docker OSX Native
As of June 2016, you can run Docker natively on OS X (and Windows I guess), http://www.docker.com/products/overview

## Vagrant-Approach
To unburden myself from the pain running MySQL (the only DB that was both freely available and capable fullfilling my requirements on a local machine) on OS X natively, I decided to run it via Docker's standard MySQL image as Docker was not available natively before 2016 (Beta, since June freely available).

As OS X doesn't support Docker natively and needs a VM for it, I decided to also control the VM creation (instead of using boot2docker or Kitematic) with Vagrant and CoreOS.

You can find the Vagrantfile below und Vagrantfile.example. If you copy it to the project root, keep in mind that a full image will be created under .vagrant, which you probably won't like to commit :)

In general, the setup process looks like this (on Mac OS X, IntelliJ IDEA):

* Vagrant must be installed, got it from [https://www.vagrantup.com/downloads.html](the official Vagrant website). N.B. - I used also the VMWare Fusion provider.
* Next the Vagrant box needs to be initialised.

## Vagrant
    v$>git clone https://github.com/coreos/coreos-vagrant/
    v$>cd coreos-vagrant/
    v$>vagrant up --provider vmware_fusion
    v$>ssh-add ~/.vagrant.d/insecure_private_key
    v$>vagrant ssh core-01 -- -A

## Docker
For getting the official MySQL docker container (https://hub.docker.com/_/mysql/)
    d$>docker run -p 0.0.0.0:3306:14572 -e MYSQL_DATABASE=quintett -e MYSQL_USER=quintett -e MYSQL_ROOT_PASSWORD=nonono -d mysql:latest




## Troubleshooting

Try
        rpcbind restart nfs-server