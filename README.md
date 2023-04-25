## **프로젝트 주제**

- 미니 커머스 프로젝트

# [프로젝트 개요]

| 항목 | 내용 |
| --- | --- |
| 프로젝트 소개 | 미니 커머스 서비스  |
| 개발 인원 | 총 1명  |
| 담당 역할 | 전체(개발, DB 세팅)  |
| 개발 기간 | 총 48일 (2023-03-07 ~ 2022-04-24) |

**⚡ [프로젝트 깃헙 바로가기](https://github.com/WooJinDo/mini-commerce)**

# [프로젝트 설명]

### 1) **사용 기술**

---

**Frontend   `HTML`** **`CSS`** **`javascript` `jQuery` `Thymeleaf`**

---

**Backend**    **`Java`** **`SpringBoot`** **`Gradle`** **`SpringSecurity`** **`SpringDataJPA`** **`Spring MVC`**

---

**Database   `MySQL`** 

---

**Collaboration & Tools   `IntelliJ`** **`Git`** **`Github`**

### 2) **ERD**

![mini_commerce_ERD](https://user-images.githubusercontent.com/90567411/234384633-c22604dd-7a29-4ef2-a29e-7c977bf7b5a9.JPG)


### 3) 프로젝트 전체 구현 기능

1. **주요 기능 테이블**
    
    `MEMBER`- 쇼핑몰 회원 정보 테이블
    
    `CART`- 회원의 장바구니 목록 테이블
    
    `CART_PRODUCT`- 장바구니에 담긴 상품 정보 테이블
    
    `ORDERS`- 쇼핑몰 회원들의 주문 목록 테이블(보류)
    
    `ORDER_PRODUCT`- 쇼핑몰 상품 정보 테이블(보류)
    
    `PRODUCT_IMG`- 상품에 대한 이미지 정보를 담고 있는 테이블
    
    `CATEGORY`- 상품 카테고리에 대한 정보를 담고 있는 테이블
    
    `CATEGORY_IMG`- 상품에 카테고리에 대한 이미지 정보를 담고 있는 테이블
    

2. **회원 가입 및 로그인**
    



- 회원가입
    - 유효한 이메일 주소가 있으면 회원 가입이 가능하다
    - 가입하려는 이메일이 중복이면 커스텀 익셉션에 걸려 `GlobalExceptionHandler` 로 보내진다
    - 우편 번호 입력을 위해 다음 우편번호 API 적용
    - @Valid 어노테이션을 통해 dto 값이 유효성 검사를 통과하지 못하면 `GlobalExceptionHandler`
    
    에서 처리할 수 있도록 하였습니다.
    
- 로그인
    - 이메일을 통하여 로그인
    - spring security를 사용하여 로그인, 로그아웃 처리

3. **카테고리 등록, 상품 등록 및 수정(상세)**
    

- 카테고리
    - spring security를 이용하여 관리자로 로그인할 때 카테고리 관리 메뉴가 보여진다
    - 카테고리를 생성하면 메인화면에 보여지게 된다
    - 파일 업로드 시 선택하지 않고 등록을 시도할 경우`MissingServletRequestPartException` 을 이용하여 `GlobalExceptionHandler` 에서 처리
    - @Valid 어노테이션을 통해 dto 값이 유효성 검사를 통과하지 못하면 `GlobalExceptionHandler`
    
    에서 처리할 수 있도록 하였습니다.
    
- 상품
    - spring security를 이용하여 관리자로 로그인할 때 상품 관리 메뉴가 보여진다
    - 카테고리를 생성하고 상품을 등록하면 화면에 출력됩니다.
    - 상품 등록, 수정화면에서 게시 여부를 통해 상품이 공개, 비공개로 나뉘어 진다
    - @Valid 어노테이션을 통해 dto 값이 유효성 검사를 통과하지 못하면 `GlobalExceptionHandler`
    
    에서 처리할 수 있도록 하였습니다.
    

4. **카테고리별, 상품 상세, 장바구니**


- 상품 and 상세
    - 상품 페이지는 카테고리를 선택하면 해당 상품들이 출력
    - 상세 페이지는 재고 이상으로 장바구니에 담을 수 없고, 담을 시 커스텀 익셉션에 걸려 `GlobalExceptionHandler` 로 보내진다
    - 수량 변경 시 결제 금액이 동시에 변경되게 하였다
- 장바구니
    - 재고 이상으로 장바구니에 담을 수 없고, 담을 시 커스텀 익셉션에 걸려 `GlobalExceptionHandler` 로 보내진다
    - 선택 박스를 클릭하면 총 주문 금액이 출력되도록 하였고, 수량 변경을 하면 가격과
        
        총 주문 금액이 변경된다
        
    - 선택 박스를 클릭 하고 삭제 하고 싶은 상품을 전체 또는 개별 삭제 가능하다
