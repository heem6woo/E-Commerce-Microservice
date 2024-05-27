<!-- 헤더 -->
<h1 align="center">🛍️ 쇼핑몰 서비스 애플리케이션 (마이크로서비스 아키텍처) 🛍️</h1>
<p align="center">
  <img src="https://github.com/ooANAoo/Spring-Cart-Microservice-E-Commerce-Platform/assets/43637355/4a8710be-256c-4fa9-9a64-0f281c3b998e" alt="쇼핑몰 로고" width="300" height="300">

</p>
<p align="center">이 프로젝트는 Spring Boot, Kafka, gRPC, AWS RDS를 사용하여 구축된 마이크로서비스 아키텍처 기반의 서비스 애플리케이션입니다. 가상 쇼핑몰 내에서 결제, 주문, 상품, 회원, 리뷰 서비스를 제공합니다.</p>

<!-- 주요 기술 -->
<h2 align="center">주요 기술</h2>
<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2.5-green" alt="Spring Boot 버전">
  <img src="https://img.shields.io/badge/Kafka-2.8.0-red" alt="Kafka 버전">
  <img src="https://img.shields.io/badge/gRPC-1.39.0-blue" alt="gRPC 버전">
  <img src="https://img.shields.io/badge/AWS%20RDS-Latest-orange" alt="AWS RDS 버전">
</p>

<!-- 서비스 개요 -->
<h2 align="center">서비스 개요</h2>
<ol>
  <li><strong>결제 서비스:</strong> 사용자 결제 정보를 처리하고 거래를 완료합니다.</li>
  <li><strong>주문 서비스:</strong> 사용자 주문을 처리하고 주문 상태를 업데이트합니다.</li>
  <li><strong>상품 서비스:</strong> 쇼핑몰에서 제공하는 상품 정보를 관리하고 제공합니다.</li>
  <li><strong>회원 서비스:</strong> 사용자 계정 정보를 관리하고 쇼핑몰 인증을 처리합니다.</li>
  <li><strong>리뷰 서비스:</strong> 사용자가 상품에 대한 리뷰를 작성하고 관리할 수 있게 합니다.</li>
</ol>
<h2 align="center">서비스 상세</h2>
<details style="margin-bottom: 15px;">
<ol>
    <li>
        <span class="toggle-button" onclick="toggleContent('payment-service')"><strong>결제 서비스:</strong></span>
        <div id="payment-service" class="toggle-content">
            <p><strong>기능:</strong> 사용자 결제 정보를 안전하게 처리하고 거래를 완료합니다.</p>
            <p><strong>주요 기능:</strong></p>
            <ul>
                <li>다양한 결제 수단 지원 (신용카드, 은행 송금, 모바일 결제 등)</li>
                <li>결제 보안 강화 (SSL 인증, 2단계 인증 등)</li>
                <li>결제 실패 시 알림 및 재시도 기능</li>
                <li>결제 내역 조회 및 영수증 발급</li>
            </ul>
            <p><strong>기술 스택:</strong></p>
            <ul>
                <li><strong>gRPC:</strong> 결제 서비스와 다른 마이크로서비스 간의 통신에 사용됩니다. 빠르고 효율적인 데이터 전송을 보장합니다.</li>
                <li><strong>Kafka:</strong> 결제 이벤트를 실시간으로 처리하고 분석을 위해 메시지 큐에 전송합니다.</li>
            </ul>
        </div>
    </li>
    <li>
        <span class="toggle-button" onclick="toggleContent('order-service')"><strong>주문 서비스:</strong></span>
        <div id="order-service" class="toggle-content">
            <p><strong>기능:</strong> 사용자 주문을 처리하고 주문 상태를 실시간으로 업데이트합니다.</p>
            <p><strong>주요 기능:</strong></p>
            <ul>
                <li>주문 생성 및 확인</li>
                <li>주문 상태 추적 (주문 접수, 처리 중, 배송 중, 배송 완료 등)</li>
                <li>주문 변경 및 취소 기능</li>
                <li>주문 내역 조회 및 관리</li>
            </ul>
            <p><strong>기술 스택:</strong></p>
            <ul>
                <li><strong>gRPC:</strong> 주문 서비스와 다른 서비스 간의 효율적인 통신을 지원합니다.</li>
                <li><strong>Kafka:</strong> 주문 생성 및 상태 업데이트 이벤트를 처리하고 관련 서비스에 전달합니다.</li>
            </ul>
        </div>
    </li>
 <li>
    <span class="toggle-button" onclick="toggleContent('product-service')"><strong>상품 서비스:</strong></span>
    <div id="product-service" class="toggle-content">
        <p><strong>기능:</strong> 쇼핑몰에서 제공하는 상품 정보를 관리하고 제공합니다.</p>
        <p><strong>주요 기능:</strong></p>
        <ul>
            <li>상품 등록, 수정 및 삭제</li>
            <li>상품 상세 정보 제공 (설명, 가격, 재고 상태 등)</li>
            <li>상품 검색 및 필터 기능</li>
            <li>상품 카테고리 및 태그 관리</li>
        </ul>
        <p><strong>기술 스택:</strong></p>
        <ul>
            <li><strong>gRPC:</strong> 상품 정보 요청 및 관리 서비스를 다른 서비스와 통합합니다.</li>
            <li><strong>KafkaStream:</strong> 상품 정보 변경 이벤트를 브로드캐스트하여 다른 서비스들이 최신 정보를 유지할 수 있도록 합니다.</li>
        </ul>
        <p><strong>연동 서비스:</strong></p>
        <p>상품 서비스는 다음과 같은 다른 서비스들과 연동되어 동작합니다:</p>
        <ul>
            <li><strong>Member 서비스:</strong> gRPC를 통해 아이템 ID를 반환합니다.</li>
            <li><strong>Order 서비스:</strong> 결제 서비스와 상품 서비스에 주문 토픽을 보내면, 상품 서비스는 이를 받아 재고 여부를 확인합니다. 재고가 있으면 주문 상태를 '참'으로 변경하여 스톡 토픽에 정보를 실어 오더 서비스에 보냅니다.</li>
        </ul>
        <p>오더 서비스는 상품 서비스에서 받은 스톡 토픽과 결제 서비스에서 받은 페이먼트 토픽을 KafkaStream으로 받아, 둘 다 '참'일 경우 주문을 완료 처리하고, 하나라도 '거짓'일 경우 롤백 처리를 합니다.</p>
    </div>
</li>
        <span class="toggle-button" onclick="toggleContent('member-service')"><strong>회원 서비스:</strong></span>
        <div id="member-service" class="toggle-content">
            <p><strong>기능:</strong> 사용자 계정 정보를 관리하고 쇼핑몰 인증을 처리합니다.</p>
            <p><strong>주요 기능:</strong></p>
            <ul>
                <li>회원 가입 및 로그인</li>
                <li>회원 정보 수정 및 탈퇴</li>
                <li>비밀번호 변경 및 찾기 기능</li>
                <li>회원 등급 및 포인트 시스템</li>
            </ul>
            <p><strong>기술 스택:</strong></p>
            <ul>
                <li><strong>gRPC:</strong> 회원 서비스와 다른 서비스 간의 통신을 최적화합니다.</li>
                <li><strong>Kafka:</strong> 회원 가입 및 정보 변경 이벤트를 처리하여 관련 서비스에 전파합니다.</li>
            </ul>
        </div>
    </li>
    <li>
        <span class="toggle-button" onclick="toggleContent('review-service')"><strong>리뷰 서비스:</strong></span>
        <div id="review-service" class="toggle-content">
            <p><strong>기능:</strong> 사용자가 상품에 대한 리뷰를 작성하고 관리할 수 있게 합니다.</p>
            <p><strong>주요 기능:</strong></p>
            <ul>
                <li>리뷰 작성 및 수정</li>
                <li>리뷰 평가 (좋아요, 신고 등)</li>
                <li>리뷰 조회 및 필터링</li>
                <li>리뷰 통계 제공 (평균 평점, 리뷰 수 등)</li>
            </ul>
            <p><strong>기술 스택:</strong></p>
            <ul>
                <li><strong>gRPC:</strong> 리뷰 데이터의 실시간 처리를 위해 다른 서비스와 통신합니다.</li>
                <li><strong>Kafka:</strong> 리뷰 작성 및 수정 이벤트를 처리하고 관련 데이터 분석 서비스에 전달합니다.</li>
            </ul>
        </div>
    </li>
</ol>
</details>

<!-- 시작하기 -->
<h2 align="center">시작하기</h2>
<ol>
  <li>이 리포지토리를 클론합니다.</li>
  <li>각 마이크로서비스 디렉토리로 이동하여 해당 서비스를 빌드합니다.</li>
  <li>Docker 및 Docker Compose를 사용하여 모든 서비스를 배포하고 실행합니다.</li>
</ol>

<!-- 기여 가이드라인 -->
<h2 align="center">기여 가이드라인</h2>
<ol>
  <li>이 리포지토리를 포크합니다.</li>
  <li>새 기능 또는 버그 수정을 위한 브랜치를 만듭니다.</li>
  <li>변경 사항을 커밋하고 푸시합니다.</li>
  <li>리뷰와 논의를 위한 풀 리퀘스트를 엽니다.</li>
</ol>

<!-- 라이선스 -->
<h2 align="center">라이선스</h2>
<p align="center">이 프로젝트는 MIT 라이선스에 따라 라이선스가 부여됩니다. 자세한 내용은 <a href="LICENSE">LICENSE</a> 파일을 참조하십시오.</p>

<!-- 백엔드 구조도 -->
<h2 align="center">백엔드 구조도</h2>
<p align="center">
    <img src="https://github.com/ooANAoo/Spring-Cart-Microservice-E-Commerce-Platform/assets/43637355/0a75c7cc-9d39-4333-a8bc-c24ad2d21d80" alt="백엔드 구조도">
</p>


<!-- 주문 요청 로직 플로우차트 -->
<h2 align="center">주문 요청 로직 플로우차트</h2>
<p align="center">
 <img src="https://github.com/ooANAoo/Spring-Cart-Microservice-E-Commerce-Platform/assets/43637355/f0f95e93-d774-42c7-8460-b3b997432958" alt="주문 요청 로직">
</p>

<!-- API 문서 -->
<!-- 헤더 -->
<!-- 헤더 -->

<details style="margin-bottom: 15px;">
   <summary><h2>API 문서</h2></summary>
  <p align="center">이 문서는 쇼핑몰 서비스 애플리케이션의 API 엔드포인트와 그 사용 방법에 대해 설명합니다.</p>
<!-- 아이템 리소스 -->
<details style="margin-bottom: 15px;">
  <summary><h2>아이템 리소스를 다루는 API</h2></summary>
  <ul>
    <li><strong>모든 아이템 정보를 가져옴:</strong> <code>GET /items</code></li>
    <li><strong>특정 아이템 정보를 가져옴:</strong> <code>GET /items/?item-name</code> (Like)</li>
    <li><strong>카테고리별, 특정 아이템 정보를 가져옴:</strong> <code>GET /items/{item-name,category}</code></li>
    <li><strong>카테고리별, 최소 가격, 특정 아이템 정보를 가져옴:</strong> <code>GET /items/{item-name,category,min-price}</code></li>
    <li><strong>카테고리별, 최소 최대 가격, 특정 아이템 정보를 가져옴:</strong> <code>GET /items/{item-name, category, min-price, max-price}</code></li>
    <li><strong>특정 아이템 정보를 생성함:</strong> <code>POST /items/{item}</code></li>
    <li><strong>아이템 리스트 정보를 생성함:</strong> <code>POST /items/{List&lt;item&gt;}</code></li>
    <li><strong>특정 아이템 정보를 수정함:</strong> <code>PUT /items/{item}</code></li>
    <li><strong>특정 아이템 정보를 부분 수정함:</strong> <code>PATCH /items/{item}</code></li>
    <li><strong>특정 아이템 정보를 삭제함:</strong> <code>DELETE /items/{item-id}</code></li>
  </ul>
</details>

<!-- 주문 리소스 -->
<details style="margin-bottom: 15px;">
  <summary><h2>주문 리소스를 다루는 API</h2></summary>
  <ul>
    <li><strong>모든 주문 정보를 가져옴:</strong> <code>GET /orders</code></li>
    <li><strong>특정 고객의 주문 정보를 가져옴:</strong> <code>GET /orders/?z{Authorization(Bearer)}</code></li>
    <li><strong>특정 고객의 특정 아이템 주문 정보를 가져옴:</strong> <code>GET /orders/?{member-id,item-id} {Authorization(Bearer)}</code></li>
    <li><strong>물품 한개 주문의 주문 정보를 생성함:</strong> <code>POST /orders/{item}</code></li>
    <li><strong>물품 여러 개 주문의 주문 정보를 생성함:</strong> <code>POST /orders/{&lt;List&gt;items}</code></li>
    <li><strong>물품 한개 주문의 주문 정보를 전부 수정함:</strong> <code>PUT /orders/{item}</code></li>
    <li><strong>물품 한개 주문의 주문 정보를 부분 수정함:</strong> <code>PATCH /orders/{item}</code></li>
    <li><strong>물품 한개 주문의 주문 정보를 삭제함:</strong> <code>DELETE /orders/?item-id</code></li>
  </ul>
</details>

<!-- 리뷰 리소스 -->
<details style="margin-bottom: 15px;">
  <summary><h2>리뷰 리소스를 다루는 API</h2></summary>
  <ul>
    <li><strong>모든 리뷰 정보를 가져옴:</strong> <code>GET /reviews</code></li>
    <li><strong>특정 아이템의 리뷰 정보를 가져옴:</strong> <code>GET /reviews/?item-id</code></li>
    <li><strong>특정 유저의 리뷰 정보를 가져옴:</strong> <code>GET /reviews/?member-id</code></li>
    <li><strong>특정 리뷰를 삭제함:</strong> <code>DELETE /reviews/?review-id</code></li>
    <li><strong>특정 리뷰를 전부 수정함:</strong> <code>PUT /reviews/{review}</code></li>
    <li><strong>특정 리뷰를 부분 수정함:</strong> <code>PATCH /reviews/{review}</code></li>
  </ul>
</details>

<!-- 유저 리소스 -->
<details style="margin-bottom: 15px;">
  <summary><h2>유저 리소스를 다루는 API (/api)</h2></summary>
  <ul>
    <li><strong>모든 유저 정보를 가져옴:</strong> <code>GET /members</code></li>
    <li><strong>특정 유저의 정보를 가져옴:</strong></li>
    <ul>
      <li><code>GET /members/id/{member-id}</code></li>
      <li><code>GET /members/email/{email}</code></li>
    </ul>
    <li><strong>특정 유저의 구매자/판매자 상세정보 가져오기:</strong></li>
    <ul>
      <li><code>GET /members/customer-detail/email/{email}</code></li>
      <li><code>GET /members/seller-detail/email/{email}</code></li>
    </ul>
    <li><strong>특정 유저정보를 생성함:</strong> <code>POST /members/{member}</code></li>
    <li><strong>특정 유저정보를 전부 수정함:</strong> <code>PUT /members/{member}</code></li>
    <li><strong>특정 유저정보를 부분 수정함:</strong> <code>PATCH /members/{member}</code></li>
    <li><strong>특정 유저정보를 삭제함:</strong> <code>DELETE /members/?member-id</code></li>
  </ul>
</details>

<!-- 인증 및 인가 -->
<details style="margin-bottom: 15px;">
  <summary><h2>인증 및 인가</h2></summary>
  <ul>
    <li><strong>유저 등록:</strong> <code>POST /api/auth/register/{name,email,password,role}</code></li>
    <li><strong>로그인:</strong> <code>POST /api/auth/authenticate/{email,password}</code></li>
    <li><strong>엑세스 토큰 재발급:</strong> <code>POST /api/auth/access-token/{Authorization(Bearer)}</code></li>
  </ul>
</details>

<!-- 비밀번호 변경 -->
<details style="margin-bottom: 15px;">
  <summary><h2>비밀번호 변경</h2></summary>
  <ul>
    <li><strong>비밀번호 변경:</strong> <code>PATCH /api/change-password/{currentPassword,newPassword,confirmationPassword} {Authorization(Bearer)}</code></li>
  </ul>
</details>

<!-- 구매자 상세정보 -->
<details style="margin-bottom: 15px;">
  <summary><h2>구매자 상세정보</h2></summary>
  <ul>
    <li><strong>구매자 상세정보 생성:</strong> <code>POST /api/customers/customer-detail/{age,gender,address} {Authorization(Bearer)}</code></li>
    <li><strong>구매자 상세정보 업데이트:</strong> <code>PATCH /api/customers/customer-detail/{age,gender,address} {Authorization(Bearer)}</code></li>
  </ul>
</details>

<!-- 판매자 상세정보 -->
<details style="margin-bottom: 15px;">
  <summary><h2>판매자 상세정보</h2></summary>
  <ul>
    <li><strong>판매자 상세정보 생성:</strong> <code>POST /api/sellers/seller-detail/{licence,address,domain,age} {Authorization(Bearer)}</code></li>
    <li><strong>판매자 상세정보 업데이트:</strong> <code>PATCH /api/sellers/seller-detail/{licence,address,domain,age} {Authorization(Bearer)}</code></li>
  </ul>
</details>
</details>
