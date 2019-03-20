if [ "$1" != "" ]; then
    echo 'CSID = ' $1
	ssh -X $1@thetis.ugrad.cs.ubc.ca
else
    echo "Usage: sh login.sh < CS ID > eg. sh login.sh b3a0b"
fi
