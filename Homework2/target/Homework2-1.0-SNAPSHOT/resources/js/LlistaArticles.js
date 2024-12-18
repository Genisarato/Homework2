/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
$(document).ready(function () {
    // Llamada a la función para obtener y listar los artículos al cargar la página
    listarArticulos();

    function listarArticulos() {
        $.ajax({
            url: 'http://localhost:8080/Homework1/rest/api/v1/article/all', // Endpoint REST definido
            type: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                mostrarArticulos(data);
            },
            error: function (xhr, status, error) {
                console.error('Error al obtener los artículos: ', error);
                $('.game-container').html('<p>No se pudieron cargar los artículos.</p>');
            }
        });
    }

    function mostrarArticulos(articulos) {
        const contenedor = $('.game-container');
        contenedor.empty(); // Limpiar cualquier contenido previo

        if (articulos && articulos.length > 0) {
            articulos.forEach(article => {
                const item = `
                    <div class="grid-item">
                        <h3>${article.title}</h3>
                        <p>${article.content}</p>
                        <span>Autor: ${article.author}</span>
                    </div>
                `;
                contenedor.append(item); // Agregar el contenido al DOM
            });
        } else {
            contenedor.html('<p>No hay artículos disponibles en este momento.</p>');
        }
    }
});


