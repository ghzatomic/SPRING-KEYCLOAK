Criar um realm ( caio-realm )
Criar um client ( caio-paulucci )
No client scopes, criar um scope chamado openid com o tipo default
colocar no valid redirect urls : *
colocar no web origins: *

No realm-settings, deixar o require SSL como none e user-managed em on

Criar um usuario ( caio )



para pegar o token via api : 
( GET ) {{server}}/realms/{{realm}}/protocol/openid-connect/token

Para logar :
( GET ) {{server}}/realms/{{realm}}/protocol/openid-connect/auth?response_type=code&client_id=jwtClient


** CAS **

Tem como criar um cliente utilizando o protocolo cas, mudando ali o client type