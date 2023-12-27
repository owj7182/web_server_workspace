
document.querySelector("#btn1").onclick = () => {
    console.log(12);
    $.ajax({
        url: `${contextPath}/xml/sample.xml`,
        method : "get",
        dataType : "xml",
        success(xmlDoc){
            const tbody = document.querySelector("#books tbody");
            tbody.innerHTML = ''; // 초기화

            // xml문서를 응답받아 parsing, javascript객체(document)로 반환
            console.log(xmlDoc); // xml문서를 파싱해서 document객체 반환

            const root = xmlDoc.querySelector(":root");
            console.dir(root); // <books></books>
            const value = root.getAttribute("myattr"); // 사용자속성
            console.log(value);

            // const books = root.chidren;
            const books = xmlDoc.querySelectorAll("book");
            books.forEach((book) => {
               console.log(book);
               const [subject, title, author] = book.children;
               console.log(subject.textContent, title.textContent, author.textContent);
               tbody.innerHTML += `
                <tr>
                    <td>${subject.textContent}</td>
                    <td>${title.textContent}</td>
                    <td>${author.textContent}</td>
                </tr>     
               `;
            });

        }
    })
};

document.querySelector("#btn-product").addEventListener('click', (e) => {
    console.log(e.target);
    $.ajax( {
        url : `${contextPath}/xml/sample-product.xml`,
        method : "get",
        dataType : "xml",
        success(xmlDoc) {
            const tbody = document.querySelector("#products tbody");
            tbody.innerHTML = '';

            // console.dir(xmlDoc);
            const products = xmlDoc.querySelectorAll("Product");
            products.forEach((product) => {
                let tr = "<tr>";
                [...product.children].forEach((attr) => {
                    console.log(attr);
                    tr += `<td>${attr.textContent}</td>`;
                });
                tr += "</tr>";
                tbody.innerHTML += tr;
            });
        }
    })
});

// document.querySelector("#btn-product").addEventListener('click', (e) => {
//     console.log(e.target);
//     $.ajax( {
//         url : `${contextPath}/xml/sample-product.xml`,
//         method : "get",
//         dataType : "xml",
//         success(xmlDoc) {
//             const tbody = document.querySelector("#products tbody");
//             tbody.innerHTML = '';
//
//             // console.dir(xmlDoc);
//             const products = xmlDoc.querySelectorAll("Product");
//             products.forEach((product) => {
//                 console.log(product);
//                 const[
//                     Product_ID, SKU, Name, Product_URL, Price, Retail_Price, Thumbnail_URL,
//                     Search_Keywords, Description, Category, Category_ID, Brand, Child_SKU,
//                     Child_Price, Color, Color_Family, Color_Swatches, Size, Shoe_Size,
//                     Pants_Size, Occassion, Season, Badges, Rating_Avg, Rating_Count,
//                     Inventory_Coun, Date_Created] = product.children;
//
//                 tbody.innerHTML +=`
//                 <tr>
//                     <td>${Product_ID.textContent}</td>
//                     <td>${SKU.textContent}</td>
//                     <td>${Name.textContent}</td>
//                     <td>${Product_URL.textContent}</td>
//                     <td>${Price.textContent}</td>
//                     <td>${Retail_Price.textContent}</td>
//                     <td>${Thumbnail_URL.textContent}</td>
//                     <td>${Search_Keywords.textContent}</td>
//                     <td>${Description.textContent}</td>
//                     <td>${Category.textContent}</td>
//                     <td>${Category_ID.textContent}</td>
//                     <td>${Brand.textContent}</td>
//                     <td>${Child_SKU.textContent}</td>
//                     <td>${Child_Price.textContent}</td>
//                     <td>${Color.textContent}</td>
//                     <td>${Color_Family.textContent}</td>
//                     <td>${Color_Swatches.textContent}</td>
//                     <td>${Size.textContent}</td>
//                     <td>${Shoe_Size.textContent}</td>
//                     <td>${Pants_Size.textContent}</td>
//                     <td>${Occassion.textContent}</td>
//                     <td>${Season.textContent}</td>
//                     <td>${Badges.textContent}</td>
//                     <td>${Rating_Avg.textContent}</td>
//                     <td>${Rating_Count.textContent}</td>
//                     <td>${Inventory_Coun.textContent}</td>
//                     <td>${Date_Created.textContent}</td>
//                 </tr>`;
//             });
//         }
//     })
// });

document.querySelector("#btn-celeb").addEventListener('click', (e) => {
   $.ajax({
       url : `${contextPath}/xml/celeb/findAll`,
       method : "get",
       dataType : "xml",
       success(xmlDoc) {
           console.log(xmlDoc);
           const tbody = document.querySelector("#celebs tbody");
           tbody.innerHTML = '';

           const celebs = xmlDoc.querySelectorAll("Celeb");
           celebs.forEach((celeb) => {
               const [id, name, profile, type] = celeb.children;
               console.log(id, name, profile, type);
               tbody.innerHTML += `
               <tr>
                    <td>${id.textContent}</td>
                    <td>
                        <img src="${contextPath}/images/celeb/${profile.textContent}" alt="">
                    </td>
                    <td>${name.textContent}</td>
                    <td>${type.textContent}</td>
               </tr>`;
           });

       }
   });
});