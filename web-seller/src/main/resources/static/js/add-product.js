
            var imageHovers = document.querySelectorAll(".show-image-old");
            var imageZooms = document.querySelectorAll(".image-zoom>img");
            for(var j = 0; j < imageHovers.length; j++){
                if(imageHovers[j].value){
                    imageHovers[j].classList.add("image-hover");
                    imageZooms[j].src ="/products/images/"+ imageHovers[j].value;
                }
            }




            var btnAddRow = document.querySelector("#addRow");
            var tableBody = document.querySelector("#table-body");


            function loadList(nodeRoot, rows) {
                console.log(rows);
                rows.forEach(element => {
                    nodeRoot.appendChild(element);

                });
            }

            btnAddRow.addEventListener("click", function (e) {
                e.preventDefault();
                var rows = document.querySelectorAll(".table-row");
                console.log(rows.length);
                tableBody.innerHTML = '';

                var tr = document.createElement("tr");

                tr.classList.add("table-row");
                var i = rows.length++;
                tr.innerHTML = `
                    <td>
                        <input required  oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Màu không được để trống')" id="optionRequests${i}.color" name="optionRequests[${i}].color" type="text" class="input-table">
                    </td>
                    <td>
                        <input name="optionRequests[${i}].price" type="number"  step="0.1" min="1" class="input-table">
                    </td>
                    <td>
                        <input name="optionRequests[${i}].quantity"  type="number"  min="1" class="input-table">
                    </td>
                    <td>
                        <input required oninput="setCustomValidity('')" oninvalid="this.setCustomValidity('Cần có SKU')" name="optionRequests[${i}].sku" type="text" class="input-table">

                    </td>
                    <td class="position-relative">
                        <input
                            name="optionRequests[${i}].image" type="text" readonly
                            class="input-table show-image-old image-old">
                            <div class="position-absolute  image-zoom">
                                                        <img class="w-100 img-thumbnail"  src="/image/default-image.jpg" alt="">
                                                    </div>
                    </td>

                    <td class="d-flex justify-content-center align-items-center">
                        <input oninput="setCustomValidity('')"
                            oninvalid="this.setCustomValidity('Vui lòng chọn ảnh sản phẩm')"
                            required name="optionRequests[${i}].imageFile"
                            type="file" class="imageFile me-2" style="width: 100px;" name="imageFile" id="imageFile"> |
                        <button class="btnRemoveRow btn btn-danger ms-2"><i class="bi bi-trash"></i></button>
                    </td>
                `;
                tr.addEventListener("click", function (tr) {

                    var fr = new FileReader();
                    fr.onload = function () {
                        document.getElementById("image").src = fr.result;
                    }
                    var parentElement = tr.target.parentElement;
                    if (parentElement.tagName === 'TD')
                        parentElement = parentElement.parentElement;
                    var fileElelement = parentElement.querySelector(".imageFile");
                    if(fileElelement)
                        var file = fileElelement.files[0];
                    if (file)
                        fr.readAsDataURL(file);
                });

                console.log(tr);
                rows = [...rows, tr];
                loadList(tableBody, rows);
                addRequiredPickImage();
                removeRowHandler();
                eventListBtnImage();
                console.log(rows);
            });

            function addRequiredPickImage(){
                var rows = document.querySelectorAll(".table-row");
                rows.forEach(element => {
                    var imageOld = element.querySelector(".image-old");
                    var imageFile = element.querySelector(".imageFile");
                    if(!imageOld.value && imageFile.files.length == 0){
                        element.setAttribute("required", '');
                    }
                });
            }

            addRequiredPickImage();

            function removeRowHandler() {
                var btnRemoveRows = document.querySelectorAll(".btnRemoveRow");

                    btnRemoveRows.forEach(element => {
                        element.addEventListener("click", function (e) {
                            e.preventDefault();
                            var totalRow = document.querySelectorAll(".btnRemoveRow").length;
                            if (totalRow> 1){
                                if (element.parentElement.parentElement.tagName === 'TR'){
                                    tableBody.removeChild(element.parentElement.parentElement)
                                }
                            }
                        });
                    });
            }
            removeRowHandler();

            var tableRowHandler = function (e) {
                var fr = new FileReader();
                fr.onload = function () {
                    document.getElementById("image").src = fr.result;
                }
                if (e.querySelector(".imageFile").files[0])
                    fr.readAsDataURL((e.querySelector(".imageFile").files[0]));
            }

            var eventListBtnImage = function () {
                var imageButtons = document.querySelectorAll(".imageFile");
                for (var i = 0; i < imageButtons.length; i++) {
                    imageButtons[i].onchange = function (evt) {
                        var tgt = evt.target || window.event.srcElement,
                            files = tgt.files;

                        // FileReader support
                        if (FileReader && files && files.length) {
                            var fr = new FileReader();

                            fr.onload = function () {
                                document.getElementById("image").src = fr.result;
                            }
                            fr.readAsDataURL(files[0]);
                        }
                    }
                }
            }

            eventListBtnImage();