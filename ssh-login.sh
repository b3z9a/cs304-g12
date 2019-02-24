if [ "$1" != "" ]; then
    echo 'CSID = ' $1
	ssh -X $1@thetis.ugrad.cs.ubc.ca
else
    echo "Usage: sh ssh-login.sh < CS ID > eg. sh ssh-login.sh b3a0b"
fi
