## **프로젝트 주제**


### 미니 커머스 서비스


총 48일 (2023-03-07 ~ 2023-04-24)

개발 인원 :  1명 

- REST API 개발
    - 회원가입 등록
    - 카테고리 등록
    - 상품 등록, 수정
    - 장바구니 상품 추가, 삭제, 수량 변경
- 다음 우편번호 API 사용
- DB 설계 및 설정
    
    

### 1) **사용 기술**

---

**Frontend**    **`javascript` `jQuery` `Thymeleaf` `axios`**

---

**Backend**    **`Java`** **`SpringBoot`** **`mybatis`** **`SpringSecurity`** **`SpringDataJPA`** **`Spring MVC`**

---

**Database   `MySQL`** 

---

**Collaboration & Tools  `IntelliJ` `Git` `Github`**

### 2) **ERD**

![mini_commerce_ERD_수정본 JPG](https://user-images.githubusercontent.com/90567411/236543890-cb6c363e-5bf0-4aa2-bb5b-ea4597db653c.jpg)


### 3) 프로젝트 전체 구현 기능

1. **주요 기능 테이블**
    
    `MEMBER` - 쇼핑몰 회원 정보 테이블
    
    `CART` - 회원의 장바구니 목록 테이블
    
    `CART_PRODUCT` - 장바구니에 담긴 상품 정보 테이블
    
    `PRODUCT` - 상품에 대한 정보를 담고 있는 테이블
    
    `PRODUCT_IMG` - 상품에 대한 이미지 정보를 담고 있는 테이블
    
    `CATEGORY` - 상품 카테고리에 대한 정보를 담고 있는 테이블
    
    `CATEGORY_IMG` - 상품에 카테고리에 대한 이미지 정보를 담고 있는 테이블
    

1. **회원 가입 및 로그인**
    
https://file.notion.so/f/s/82dc803d-0929-4e48-8799-e1360c939323/%EB%85%B9%ED%99%94_2023_04_24_13_03_47_271.mp4?id=5d86e75c-1d49-4d66-ba05-cd91a03da2aa&table=block&spaceId=22ba97ba-d04f-4420-8e04-a5adb596b5b0&expirationTimestamp=1683399145695&signature=3hTvZKPtjSWgBLeCWQLmjATtf8yhnNVNDvmn4xfdT28
    

- 회원가입
    - 유효한 이메일 주소가 있으면 회원 가입이 가능하다
    - 가입하려는 이메일이 중복이면 경고창이 뜬다
    - 우편 번호 입력을 위해 다음 우편번호 API 적용
- 로그인
    - 이메일을 통하여 로그인
    - spring security를 사용하여 로그인, 로그아웃 처리


2. **카테고리 등록, 상품 등록 및 수정(상세)**
    
https://file.notion.so/f/s/359930b3-0f5a-449c-9151-43d71f9135d2/%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC_%EC%83%81%ED%92%88_%EB%93%B1%EB%A1%9D_%EC%88%98%EC%A0%95.mp4?id=ef3690c3-7c3d-4222-bcb6-b45f75cc18a9&table=block&spaceId=22ba97ba-d04f-4420-8e04-a5adb596b5b0&expirationTimestamp=1683399177552&signature=hf-cQUBZ_IB-WfWgLZDabMv3_YNfsuABvJv7xpY0-VE
    

- 카테고리
    - 카테고리를 생성하면 메인화면에 보여지게 된다
- 상품
    - 카테고리를 생성하고 상품을 등록하면 화면에 출력됩니다
    - 상품 등록, 수정화면에서 게시 여부를 통해 상품을 공개, 비공개로 변경할 수 있다


3. **카테고리별, 상품 상세, 장바구니**

https://file.notion.so/f/s/f11e7782-3169-421e-a374-766e0e4b98c6/%EC%B9%B4%ED%85%8C%EA%B3%A0%EB%A6%AC%EB%B3%84_%EC%83%81%ED%92%88%EC%83%81%EC%84%B8_%EC%9E%A5%EB%B0%94%EA%B5%AC%EB%8B%88.mp4?id=11e926b1-c601-4649-9a81-fee813fbdb0b&table=block&spaceId=22ba97ba-d04f-4420-8e04-a5adb596b5b0&expirationTimestamp=1683399180203&signature=tn7o8UZJZRp7Ll5OrJJqgD8xHnoA-vcIkZXui1ESzdc

- 상품화면 and 상품상세화면
    - 상품 화면에서는 원하는 카테고리를 선택하면 해당 상품들이 출력
    - 상품 상세화면에서는 재고 이상으로 장바구니에 담을 수 없다
    - 수량 변경 시 결제 금액이 동시에 변경된다
- 장바구니
    - 재고 이상으로 장바구니에 담을 수 없다
    - 선택 박스를 클릭하면 총 주문 금액이 출력되고, 수량 변경을 하면
        
        가격과 총 주문 금액이 변경된다
        
    - 선택 박스를 클릭 하고 삭제 하고 싶은 상품을 전체 또는 개별 삭제 가능하다
    

### 4) 프로젝트에서의 학습

### DTO 관리 - Inner class

- DTO 클래스 파일의 개수가 너무 많아져 관리하기가 어려운 문제가 발생했습니다. 하나의 도메인에 생기는 여러개의 DTO를 한번에 관리하는 방법이 없을까..? 라는 고민을 하게 되었습니다.
    
    그래서 **내부 클래스 적용을** 하였습니다.
    
    ```java
    public class MemberDto {
    
        @Getter @Setter
        @ToString
        public static class RegisterRequest{
            /* 이름 */
            @NotBlank(message = "이름을 입력해주세요.")
            private String userName;
    
            /* 비밀번호 */
            @Size(min = 6, max = 13, message = "비밀번호는 6자리 이상 13자리 이하로 입력해주세요.")
            private String password;
    
            /* 이메일 */
            @NotBlank(message = "이메일을 입력해주세요.")
            private String email;
    
            /* 휴대폰 번호 */
            @NotBlank(message = "핸드폰 번호를 입력해주세요.")
            private String userPhone;
    
            /* 우편번호 */
            @NotBlank(message = "우편번호 찾기를 통해 입력해주세요.")
            private String zipcode;
    
            /* 주소 */
            @NotBlank(message = "우편번호 찾기를 통해 입력해주세요.")
            private String address;
    
            /* 상세주소 */
            @NotBlank(message = "상세주소를 입력해주세요.")
            private String addressDetail;
        }
    
        @Getter @Setter
        @Builder
        public static class RegisterResponse{
            private boolean success;
    
            public static RegisterResponse fromEntity(boolean success) {
                return RegisterResponse.builder()
                        .success(success)
                        .build();
            }
        }
    
    }
    ```
    
    내부 클래스를 적용하여 클래스 파일의 개수를 줄이고 MemberDto하나로 관련된 Dto를 묶음으로서 빠르게 원하는 Dto를 찾을 수 있습니다.
    
    Inner class를 사용하여 배운점은
    
    Inner 클래스는 일반적으로 static으로 선언합니다. 이유는 Inner 클래스가 외부 클래스의 인스턴스에 대한 참조를 갖지 않도록 하기 위해서입니다. 또한, static으로 선언하면 외부 클래스의 인스턴스가 생성되지 않아도 Inner 클래스의 인스턴스를 생성할 수 있기 때문입니다.
    
    그리고 Inner 클래스를 사용하면 DTO 클래스를 구조화하고 가독성을 높일 수 있습니다.
    
    하지만 Inner class가 너무 많아지면 코드 구조가 복잡해지고 가독성이 떨어질 수 있겠다 생각했습니다. 
    

---

### @Valid 유효성 검증

- 사용자가 입력한 데이터를 검증하기 위해 사용

```java
public class MemberDto {

    @Getter @Setter
    @ToString
    public static class RegisterRequest{
        /* 이름 */
        @NotBlank(message = "이름을 입력해주세요.")
        private String userName;

        /* 비밀번호 */
        @Size(min = 6, max = 13, message = "비밀번호는 6자리 이상 13자리 이하로 입력해주세요.")
        private String password;

        /* 이메일 */
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        /* 휴대폰 번호 */
        @NotBlank(message = "핸드폰 번호를 입력해주세요.")
        private String userPhone;

        /* 우편번호 */
        @NotBlank(message = "우편번호 찾기를 통해 입력해주세요.")
        private String zipcode;

        /* 주소 */
        @NotBlank(message = "우편번호 찾기를 통해 입력해주세요.")
        private String address;

        /* 상세주소 */
        @NotBlank(message = "상세주소를 입력해주세요.")
        private String addressDetail;
    }

}
```

- @Valid 가 붙은 DTO가 컬럼값 유효성 검사에 실패하면 500 에러가 발생하는데, 이를 그냥 냅두지 않고 `GlobalExceptionHandler`에서 유효성 검사 실패 정보를 `response`에 담을수 있도록 익셉션핸들러 추가

```java
/**
 * DTO @Valid 유효성 검사 실패 시
 * */
@ResponseBody
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
public ValidErrorResponse methodValidException(
MethodArgumentNotValidException e, HttpServletRequest request) {

  log.error("methodValidException is occurred. url: {}", request.getRequestURI());

  BindingResult bindingResult = e.getBindingResult();
  // DTO 에 설정한 message 값을 가져온다
  String errorMessage = bindingResult.hasErrors() ? 
	bindingResult.getFieldError().getDefaultMessage() : "";
  String errorCode = "Parameter Validate Fail";

  return new ValidErrorResponse(errorCode, errorMessage);
}

```

@Valid 어노테이션을 사용을 통해  코드의 가독성을 높이고, 입력값의 유효성 검증을 쉽게 할 수 있다는 것을 알았습니다

---

### GlobalexceptionHandler 와 CustomException

- @Controller, @RestController에서 발생한 Exception에 대해 한곳에서 처리 하기 위해 사용하였습니다

```java
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
     * 회원 에러 핸들러 - error 반환
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MemberException.class)
    public MemberErrorResponse handleMemberException(MemberException e) {
        log.error("{} is occurred.", e.getMemberErrorCode());
        return new MemberErrorResponse(e.getMemberErrorCode(), e.getErrorMessage());
    }

    /*
     * 장바구니 에러 핸들러 - error 반환
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CartException.class)
    public CartErrorResponse handleMemberException(CartException e) {
        log.error("{} is occurred.", e.getCartErrorCode());
        return new CartErrorResponse(e.getCartErrorCode(), e.getErrorMessage());
    }

    /**
     * DTO @Valid 유효성 검사 실패 시
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidErrorResponse methodValidException(
MethodArgumentNotValidException e, HttpServletRequest request) {

        log.error("methodValidException is occurred. url: {}", 
				request.getRequestURI());

        BindingResult bindingResult = e.getBindingResult();
        // DTO 에 설정한 message 값을 가져온다
        String errorMessage = bindingResult.hasErrors() ? 
				bindingResult.getFieldError().getDefaultMessage() : "";
        String errorCode = "Parameter Validate Fail";

        return new ValidErrorResponse(errorCode, errorMessage);
    }

    /**
     * 파일 업로드 - 선택하지 않고 등록을 시도할 때 발생하는 오류 처리
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    public FileErrorResponse missingServletRequestPartException(
MissingServletRequestPartException e, HttpServletRequest request) {

        log.error("missingServletRequestPartException is occurred. url: {}", 
				request.getRequestURI());

        String errorMessage = "파일을 선택해 주세요.";
        String errorCode = "Please select a file to upload";

        return new FileErrorResponse(errorCode, errorMessage);
    }

}
```

- 전역익셉션 핸들러를 통해서 공통적으로 front로 보내는 양식을 깔끔히 통일할 수도 있고, 익셉션 처리 관련해서 코드가 확실히 핸들러 객체로 모이니 코드 유지보수에도 훨씬 편하다는 것을 경험할 수 있었습니다

- CustomException을 사용하여 상세한 예외 정보를 제공할 수 있습니다.
- `Enum`과 함께 사용하여 예외에 대한 응집도를 높일 수 있습니다.

```java
@Getter
@AllArgsConstructor
public enum CartErrorCode {

  USER_EMAIL_NOT_EXIST("해당 이메일을 가진 사용자가 없습니다."),
  USER_CART_NOT_EXIST("장바구니 관련 내부 서버 오류로 장바구니 상품의 상태 변경이 어렵습니다."),
  USER_CART_PRODUCT_NOT_EXIST("장바구니 관련 내부 서버 오류로 장바구니 상품의 상태 변경이 어렵습니다."),
  PRODUCT_NOT_EXIST("선택하신 상품은 현재 장바구니에 추가할 수 없는 상품입니다."),
  OVER_QUANTITY("상품 재고량을 초과했습니다."),
  CART_OVER_QUANTITY("상품을 추가하시면 장바구니에 있는 상품 재고량을 초과합니다.");

  private final String description;
}
```

---

### CRUD

- 회원가입 등록
- 카테고리 등록
- 상품 등록, 수정
- 장바구니 상품 추가, 삭제, 수량 변경

HTTP 메서드와 URI를 이용하여 자원을 관리하고, API의 가독성과 확장성을 높이며,  JSON이나 XML 등 다양한 데이터 형식을 지원하여 클라이언트가 요청하는 데이터 형식에 맞게 응답하기 위해 RESTful API형식으로 axios 통신을 통해 json형태로 데이터를 보내어 구현

ex) 장바구니 상품 추가

```jsx
// 장바구니 상품 추가
function addCart(){
    const cartMsg = '장바구니에 해당 상품을 추가하겠습니까?';

    if(!confirm(cartMsg)){
        return false;
    }

    // 로그인 여부 확인
    if(!isLoginCheck()){
        return false;
    }

    let url = '/api/cart/addCartProduct';
    let productId = document.getElementById('productId').value;
    let cartQuantity = document.getElementById('cartQuantity').value;

    const param = {
        productId: productId
       , cartQuantity : cartQuantity
    };

    axios.post(url, param).then(function(response) {

        // 기존 response.data 가 있으면 유지, 없으면 {} 빈 객체 할당
        response.data = response.data || {};
        // 기존 response.data.header 가 있으면 유지, 없으면 {} 빈 객체 할당
        response.data.responseResultHeader = response.data.responseResultHeader || {};

        //success
        console.log(response.data);
        alert('장바구니에 상품을 추가하였습니다.');

    }).catch(function(error) {

        if (error.response) {
            //요청 후, 서버가 2xx 범위에 벗어나는 상태 코드를 주었을 때 (@Valid 유효성 검사 실패 시 익셉션 핸들러 코드 및 중복 이메일 검사)
            console.log(error.response.data);
            alert(error.response.data.errorMessage);
        }
        console.log(error);
    });
}
```

```java
/* 장바구니 상품 추가 */
@PostMapping("/api/cart/addCartProduct")
public ResponseEntity<?> addCartProduct(@RequestBody @Valid CartDto.AddCartProductRequest request, Principal principal) {

    String email = principal.getName();
    CartDto.AddCartProductResponse response = cartService.addCartProduct(request, email);

    return ResponseEntity.status(HttpStatus.OK).body(response);
}
```

---

### 장바구니 목록 삭제 (동적 쿼리 사용)

```jsx
// 선택된 체크박스를 반복문에 돌려 값 출력
 let cartProductIds = '';
document.querySelectorAll('.cartChkBox:checked').forEach(function(element) {
     // 반복해서 cartProductId를 문자열로 연결한다
     const cartProductId = element.dataset.id;
     cartProductIds = cartProductIds + cartProductId + ',';
});
// 마지막에 , 제거하기 위해
cartProductIds = cartProductIds.substr(0, cartProductIds.length-1);

let url = '/api/cart/deleteCartProduct/' + cartProductIds;
```

- 장바구니에서 check된 항목들을  foreach한다음 데이터를 붙여서 path로 넘겨준다

```java
    
  /*
 * 사용자 - 장바구니 상품 삭제
 * */
@Transactional
@Override
public void deleteCartProduct(String cartProductIds, String email) {

  String[] cartProductIdArr = cartProductIds.split(",");

  List<String> cartProductList = new ArrayList<>();
  cartProductList = Arrays.asList(cartProductIdArr);

  CartDto.DeleteCartProduct deleteCartProductDto = new CartDto.DeleteCartProduct();
  deleteCartProductDto.setCartProductList(cartProductList);

  cartMapper.deleteCartProduct(deleteCartProductDto);

}

```

- 배열로 데이터를 담은 후 MyBatis에서 ****리스트를 사용하기 위해 변환해준다

```sql
<!-- 사용자 - 장바구니 상품 삭제 -->
<delete id="deleteCartProduct" parameterType="com.zerobase.minicommerce.cart.dto.CartDto$DeleteCartProduct" >
    DELETE FROM CART_PRODUCT
    WHERE CART_PRODUCT_ID IN
    <foreach collection="cartProductList" item="cartProductId" separator="," 
			open="(" close=")" >
        #{cartProductId}
    </foreach>
</delete>
```

- **`separator`** 속성은 원소들을 구분하는 구분자를 지정합니다. 이 예제에서는 쉼표(,)를 사용하여 원소들을 구분합니다.
- **`open`** 속성과 **`close`** 속성은 각각 반복문의 시작 부분과 끝 부분에 출력할 문자열을 지정합니다. 이 예제에서는 괄호로 시작해서 괄호로 끝나는 문자열을 출력합니다.
- 이렇게 설정된 **`<foreach>`** 태그는 지정된 리스트에서 원소들을 하나씩 꺼내어 SQL 쿼리에 포함시킵니다

위와 같이 생성된 SQL 쿼리는 IN 연산자와 함께 사용되어, 리스트 원소들 중에서 조건에 맞는 데이터를 조회하거나, 삭제할 때 사용될 수 있습니다

---

### SpringSecurity 사용

- Spring Security는 인증 및 권한 부여를 처리하여 사용자가 로그인하고 로그아웃할 때 안전한 방식으로 처리됩니다. 이를 통해 사용자 인증 정보가 노출되는 것을 방지하고, 인가되지 않은 사용자가 액세스를 시도하는 것을 막기 위해 사용
- Spring Security에서 제공하는 BCryptPasswordEncoder를 사용하여 입력받은 비밀번호를 암호화
    - `String encPassword = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());`
    - 위 코드는 입력받은 비밀번호를 BCrypt 알고리즘으로 암호화한 후, 암호화된 문자열을 반환합니다. 이 암호화된 비밀번호는 보안상 안전한 방식으로 저장되어 사용될 수 있습니다
- 권한별로 페이지 접근하여 보안성을 높인다
    - hasAnyAuthority('ROLE_ADMIN') : ROLE_ADMIN 권한을 갖는 관리자만 접근
    - isAnonymous() : 로그인하지 않은 사용자에게만 접근
    - isAuthenticated() : 로그인한 사용자에게만 접근

```html

   <a sec:authorize="hasAnyAuthority('ROLE_ADMIN')" class="nav-link" href="/admin/product/list.do">
		상품 관리
	</a>

  <a sec:authorize="isAnonymous()" class="nav-link" href="/member/login">
		로그인
	</a>

  <a sec:authorize="isAuthenticated()" class="nav-link" href="/member/logout">
		로그아웃
	</a>
```

---

### 트러블 슈팅 - 상품 수량변경

- 상품 상세화면에서 장바구니에 담은 상품을 재고수량을 초과해서 중복해서 담는 경우 그대로 DB에 담겨지는 문제가 생겼습니다
1. 장바구니에 상품을 담습니다

![1](https://user-images.githubusercontent.com/90567411/236545675-00929baa-d938-49ad-9361-a03dc4e2f4ec.png)


2. 똑같은 상품을 중복해서 담습니다

![2](https://user-images.githubusercontent.com/90567411/236545739-268e1cb1-25b7-4cfa-945e-f70072b0efb6.png)


3. 재고수량이 초과되면 2번 화면에서 추가할 수 없다는 메세지가 떠야하는데 초과돼서 DB에 까지 담겼습니다

![3](https://user-images.githubusercontent.com/90567411/236546377-889dbb56-11b2-4404-81b7-ba9507d8b77b.png)

![3-1](https://user-images.githubusercontent.com/90567411/236546394-4ee69e29-6fac-4948-bcdb-de14f8a33244.png)


- 장바구니 상품 테이블에 해당 상품이 존재하고, 수량 추가를 했다면 수량을 추가해주고, 그 수량이 재고수량보다 크다면 GlobalExceptionHandler에 보내지게 되는 exception 발생시키기
- 수량이 재고수량을 초과했을 경우 DB에 insert 되지 않고, 경고 메세지 출력

![4](https://user-images.githubusercontent.com/90567411/236546467-63444007-f7a2-4dcf-9e82-22c1249fe85b.png)
![5](https://user-images.githubusercontent.com/90567411/236546476-79ff0af1-1f80-412d-afe9-2fdbdf09f9d6.png)


---

### 트러블 슈팅 - 파일 수정

- 파일을 업로드할 때 사용자가 업로드할 파일을 선택하도록 요구하는 것이 일반적입니다. 단, 파일 수정을 할 때 기존 파일을 그대로 사용하고, 선택하지 않고 등록을 시도하니 GlobalExceptionHandler에서 에러가 잡혀 경고메세지가 출력 됐습니다

![1-1](https://user-images.githubusercontent.com/90567411/236546573-ce2965f2-0209-4928-829b-18f26ff721cf.png)


- 파일을 선택하지 않고 등록을 시도하면 null 값으로 들어오게 됩니다. 그래서 에러가 발생한다
- 이를 해결하기 위해 스프링에서 파라미터 값을 처리할 때, 해당 파라미터가 필수인지 아닌지를 지정하는 옵션 required=false를 지정해 해당 필드가 존재하지 않으면 예외를 발생시키지 않고 오류 없이 처리 할 수 있다

![1-2](https://user-images.githubusercontent.com/90567411/236546608-946ecd70-1ad3-4b6e-af84-f5a5e5fba898.png)


---
