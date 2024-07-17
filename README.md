### 에러1
```
variable feignClient not initialized in the default constructor
private final NaverFeignClient feignClient;
```
* 원인 : 컴파일 시점에 서비스에 있는 @RequiredArgsConstructor가 작동하지 않았음.
* 해결 : annotationProcessor('org.projectlombok:lombok') 명시


### 에러2
```
RequestParam.value() was empty on parameter 1 of method approvalRequest
```
* 원인 : @RequestParam에 name 옵션을 설정하지 않아서 나는 에러. 
* 해결 : Build and run 설정을 Intellij IDEA 에서 Gradle로 변경하니 해결됨
* 파라미터 이름이 변수의 이름과 같으면 name 속성을 생략할 수 있는걸로 아는데?
* Gradle : 오픈소스 빌드 자동화 도구
* Intellij 빌드 방식 : 인텔리제이에서 제공하는 빌드 자동화 도구. 증분 빌드. 변경된 부분만 빌드를 하는 방식. 
<U>모든 gradle 플러그인을 지원하지는 않는다고 함.</U>

### 에러3
```
Class TestFeignClient has annotations [FeignClient] that are not used by contract Default
Parameter header has annotations [RequestHeader] that are not used by contract Default
Parameter paymentId has annotations [RequestParam] that are not used by contract Default
```
* 원인 : Feign contract가 Spring MVC 어노테이션을 인식하지 못해서 발생함.
* 해결 : @FeignClient 어노테이션 제거,  @RequestHeader은 @HeaderMap으로, @RequestParam은 @QueryMap 으로 변경