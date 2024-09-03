function deleteWarehouse(endpoint, warehouseId) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(endpoint, {
            method: "DELETE"
        }).then(res => {
            if (res.status === 204) {
                let d = document.getElementById(`warehouse${warehouseId}`);
                d.style.display = "none";
            } else {
                alert("Something Wrong!");
            }
        }).catch(error => console.error('Error:', error));
    }
}


