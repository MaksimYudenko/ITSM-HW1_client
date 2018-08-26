### HW1 client
- to build a docker image:
```
docker build -t itsm_hw1_client .
```
- to run the application:
```
docker run -t --network host itsm_hw1_client <message>
```