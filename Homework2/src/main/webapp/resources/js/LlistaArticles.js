$(document).ready(function () {
    carregarArticles();
});

function carregarArticles() {
    carregarIActualitzarArticles();
}

function carregarIActualitzarArticles() {
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

                // Construir manualmente el formato con hora primero y luego la fecha
                var fechaPublicacion = (date && !isNaN(date))
                    ? `${date.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit"})}, ${date.toLocaleDateString([], { day: "2-digit", month: "2-digit", year: "numeric" })}`
                    : "Data no disponible";

                $(".article-container").append(`
                    <div class="article" data-id="${article.n_views || 0}">
                        <div class="article-details">
                            <p class="article-title">${article.titol || "Títol no disponible"}</p>
                            <p><strong>Autor:</strong> ${article.nom_Aut || "Autor desconegut"}</p>
                            <p><strong>Publicació:</strong> ${fechaPublicacion}</p>
                            <p><strong>Descripció:</strong> ${article.descripcio || "Sense descripció"}</p>
                            <p><strong>Visualitzacions:</strong> ${article.n_views || 0}</p>
                            <p><strong>Tòpics:</strong> ${article.topics ? article.topics.join(", ") : "No disponible"}</p>
                        </div>
                    </div>
                `);
            });

            assignarEsdevenimentsArticles();
        },
        error: function (xhr, status, error) {
            console.error("Error obtenint els articles:", status, error);
        },
    });
}

function assignarEsdevenimentsArticles() {
    $(".article").off("click").on("click", function () {
        var articleId = $(this).data("id");
        // Llamar a openModal o realizar otra acción
        // openModal(articleId);
    });
}
