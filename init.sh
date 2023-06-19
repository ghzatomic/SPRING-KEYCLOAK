#/bin/sh
/opt/keycloak/bin/kc.sh start-dev
/opt/keycloak/bin/kcadm.sh config credentials --server http://localhost:8080 --realm master --user admin --password sajadv
/opt/keycloak/bin/kcadm.sh update realms/master -s sslRequired=NONE

