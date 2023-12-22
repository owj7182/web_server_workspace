const getXMLHttpRequest = () => new XMLHttpRequest();

/**
 * XMLHttpRequest
 * - readystate
 *  - 0: 시작전
 *  - 1: 로딩
 *  - 2: 로딩완료
 *  - 3: 상호작용(open & send)
 *  - 4: 완료
 */
const doGet = () => {
    // 1. XMLHttpRequest객체 생성
    const xhr = getXMLHttpRequest();
    // 2. readystatechange 핸들러 바인딩
    xhr.onreadystatechange = (e) => {
        console.log(xhr.readyState);

        if(xhr.readyState === 4) {
            if(xhr.status === 200) {
                // 비동기 통신 완료시 콜백처리
                console.log(xhr.responseText);
                document.querySelector("#target").innerHTML = xhr.responseText;
                document.testFrm.reset();
            }
        }
    }
    // 3. 연결생성 open
    const frm = document.testFrm;
    const name = frm.name;
    const num = frm.num;
    xhr.open("Get", `${contextPath}/js?name=${name.value}&num=${num.value}`);

    // 4. 전송 send
    xhr.send();

}
const doPost = () => {
    // 1. XMLHttpRequest객체 생성
    const xhr = getXMLHttpRequest();

    // 2. readystatechange 핸들러 바인딩
    xhr.onreadystatechange = (e) => {
        if(xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
            document.querySelector("#target").innerHTML = "POST " + xhr.responseText;
        }
    }

    // 3. 연결생성 open
    xhr.open("POST", `${contextPath}/js`);

    // 4. 전송 send && header.content type 설정
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    const frm = document.testFrm;
    const name = frm.name;
    const num = frm.num;
    xhr.send(`name=${name.value}&num=${num.value}`);
}