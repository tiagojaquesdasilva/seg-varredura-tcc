# REDE SOCIAL

API para app de rede social

## Funcionalidades

- Dados do usuario logado: `GET /usuario/info`
- Retorna lista de amigos usuario logado: `GET /usuario/amigos`
- Listar usuarios filtrado: `GET /usuario/buscar?filtro=value` --> value ->caracter a ser buscado no nome e email
- Listar pedidos de amizade: `GET /usuario/pedido_amizade` 
- Adicionar postagem: `POST /usuario/postar`  
- Listar postagens usuario logado e amigos: `GET /usuario/listar_postagem` 
- Curtir e descurtir postagem: `POST /usuario/{postagemId}/curtir_descurtir` 
- Adicionar comentario postagem: `POST /usuario/{postagemId}/comentar`  
- Aceita ou rejeita pedido de amizade: `PUT /usuario/{amizadeId}/alterar_amizade`
- Adicionar pedido amizade: `POST /usuario/{possivel_amigoId}/pedido`
- Editar perfil usuario: `PUT /usuario/editar_perfil`

- Listar postage de terceiro: `GET /{usuarioTerceiroId}/listar_postagem`

## Dependências

- Java 11
- PostgreSQL 14
- Criar objetos de banco e carga inicial: `data/data.sql`
- Arquivo do postman: `data/redeSocial.postman_collection.json`

password: 'test'
