$(document).ready(function () {
    carregarArticles();
});

function carregarArticles() {
    carregarIActualitzarArticles();
}

function carregarIActualitzarArticles() {
    // Mostrar el spinner
    $("#loading-spinner").show();

    $.ajax({
        url: "http://localhost:8080/Homework1/rest/api/v1/article/all",
        type: "GET",
        dataType: "json",
        success: function (articles) {
            console.log("Dades rebudes:", articles);

            if (!Array.isArray(articles)) {
                console.error("La resposta no és un array:", articles);
                return;
            }

            $(".article-container").empty();

            articles.forEach(function (article) {
                var dataPubliValida = article.data_publi ? article.data_publi.replace("[UTC]", "") : null;
                var date = dataPubliValida ? new Date(dataPubliValida) : null;

                var fechaPublicacion = (date && !isNaN(date))
                    ? `${date.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}, ${date.toLocaleDateString([], { day: "2-digit", month: "2-digit", year: "numeric" })}`
                    : "Data no disponible";

                // Imagen por defecto si no hay imagen disponible
                var imagen = article.imatge || 'https://via.placeholder.com/150';

                // Verificación y ajuste del candado
                var iconoCandado = (article.privat === true) ? '🔒' : '🔓';

                // Verificar valores antes de mostrarlos
                console.log("Imagen URL:", imagen);
                console.log("Candado:", iconoCandado);

                // Verificar si `article.topics` es un array y tiene elementos
                var topics = Array.isArray(article.topics) && article.topics.length > 0 ? article.topics.join(", ") : "No disponible";

                $(".article-container").append(`
                    <div class="article" data-id="${article.id || 0}">
                        <div class="article-details">
                            <img src="${imagen}" alt="${article.titol || "Títol no disponible"}" class="article-img">
                            <p class="article-title">${article.titol || "Títol no disponible"}</p>
                            <p><strong>${iconoCandado}</strong></p>
                            <p><strong>Autor:</strong> ${article.nom_Aut || "Autor desconegut"}</p>
                            <p><strong>Publicació:</strong> ${fechaPublicacion}</p>
                            <div class="article-views">
                                <span class="eye-icon">👁️</span> ${article.n_views}
                            </div>
                        </div>
                    </div>
                `);
            });

            assignarEsdevenimentsArticles();

            // Ocultar el spinner
            $("#loading-spinner").hide();
        },
        error: function (xhr, status, error) {
            console.error("Error obtenint els articles:", status, error);
            alert("Hi ha hagut un error al carregar els articles. Si us plau, intenta-ho més tard.");
            $("#loading-spinner").hide(); // Ocultar el spinner si ocurre un error
        }
    });
}

function assignarEsdevenimentsArticles() {
    $(".article").off("click").on("click", function () {
        var articleId = $(this).data("id");
        // Redirigir a la página de detalles del artículo
        window.location.href = `/Homework2/Web/article/${articleId}`;
    });
}
