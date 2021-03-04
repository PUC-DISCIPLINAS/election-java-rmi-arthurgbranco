# election-java-rmi-arthurgbranco

Trabalho de eleição utilizando java rmi

Autor: Arthur Gramiscelli Branco

Como rodar:
1. Altere o valor da variavel PROJECT_PATH dentro do arquivo config.sh para o caminho do projeto election-java-rmi-arthurgbranco no seu computador, ex: 
  * `echo 'PROJECT_PATH=/Users/arthur.branco/Documents/code/puc/repos/election-java-rmi-arthurgbranco"' >> ./config.sh`

2. Execute o script de compilação
  * `chmod +x compile.sh`
  * `./compile.sh`

3. De dentro da pasta raiz do projeto navegue para a pasta bin e execute o comando rmiregistry
  * `cd ./bin`
  * `rmiregistry`

4. Abra outra janela do terminal dentro da raiz do projeto e rode o script do servidor
  * `chmod +x ./server.sh`
  * `./server.sh`

5. Execute o script do cliente passando os parametros (eleitor) (candidato), ex:
  * `chmod +x ./client.sh`
  * `./client.sh "Arthur Gramiscelli Branco" "LUIZ FERNANDO SANTOS"`