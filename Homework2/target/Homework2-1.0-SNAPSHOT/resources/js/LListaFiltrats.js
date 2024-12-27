function carregarArticles(articles) {
    // Mostrar el spinner si lo tienes en la interfaz
    $("#loading-spinner").show();

    // Verificar si hay artículos
    if (!Array.isArray(articles)) {
        console.error("La respuesta no és un array:", articles);
        return;
    }

    // Limpiar la lista de artículos actual
    $(".article-container").empty();

    // Recorrer los artículos y agregarlos a la vista
    articles.forEach(function (article) {
        var dataPubliValida = article.data_publi ? article.data_publi.replace("[UTC]", "") : null;
        var date = dataPubliValida ? new Date(dataPubliValida) : null;

        var fechaPublicacion = (date && !isNaN(date))
            ? `${date.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}, ${date.toLocaleDateString([], { day: "2-digit", month: "2-digit", year: "numeric" })}`
            : "Data no disponible";

        var imagen = article.imatge || 'https://via.placeholder.com/150';
        var iconoCandado = (article.privat === true) ? '🔒' : '🔓';
        var topics = Array.isArray(article.topics) && article.topics.length > 0 ? article.topics.join(", ") : "No disponible";

        // Agregar el artículo a la lista de artículos filtrados
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

    // Ocultar el spinner si ya hemos cargado los artículos
    $("#loading-spinner").hide();

    // Asignar eventos de clic para redirigir al artículo
    assignarEsdevenimentsArticles();
}

function assignarEsdevenimentsArticles() {
    $(".article").off("click").on("click", function () {
        var articleId = $(this).data("id");
        // Redirigir a la página de detalles del artículo
        window.location.href = `/Homework2/Web/article/${articleId}`;
    });
}