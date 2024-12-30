$(document).ready(function () {
    console.log("LListaFiltrats.js se está ejecutando correctamente.");
    console.log("Variable articles:", typeof articles !== "undefined" ? articles : "No definida");
    // Procesar y renderizar los artículos
    carregarArticles(articles);
    console.log("Articles en carregarArticles:", articles);
});

function formatDate(rawDate) {
    try {
        // Parsear la fecha en formato "Mon Dec 30 15:17:24 CET 2024"
        var partes = rawDate.split(" ");
        var meses = { Jan: "01", Feb: "02", Mar: "03", Apr: "04", May: "05", Jun: "06", Jul: "07", Aug: "08", Sep: "09", Oct: "10", Nov: "11", Dec: "12" };

        var dia = partes[2]; // 30
        var mes = meses[partes[1]]; // Dec -> 12
        var año = partes[5]; // 2024
        var hora = partes[3]; // 15:17:24

        // Crear un formato ISO para que el objeto Date lo entienda
        var isoDate = `${año}-${mes}-${dia}T${hora}`;
        var date = new Date(isoDate);

        if (!isNaN(date)) {
            // Formatear la fecha como hora:minuto, día/mes/año
            return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }) + 
                ", " + date.toLocaleDateString([], { day: '2-digit', month: '2-digit', year: 'numeric' });
        } else {
            console.error("Fecha inválida:", rawDate);
            return "Fecha no disponible";
        }
    } catch (error) {
        console.error("Error procesando la fecha:", rawDate, error);
        return "Fecha no disponible";
    }
}


function carregarArticles(articles) {
    // Limpiar el contenedor antes de agregar nuevos artículos
    $("#article-container").empty();

    // Iterar sobre los artículos y renderizarlos
    articles.forEach(function (article) {
        var fechaPublicacion = formatDate(article.data_publi);

        // Imagen por defecto si no hay imagen disponible
        var imagen = article.imatge || 'https://via.placeholder.com/150';

        // Verificación y ajuste del candado
        var iconoCandado = (article.privat === true) ? '🔒' : '🔓';

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
    $("#loading-spinner").hide(); // Ocultar el spinner
}

function assignarEsdevenimentsArticles() {
    $(".article").on("click", function () {
        var articleId = $(this).data("id");
        // Redirigir a la página de detalles del artículo
        window.location.href = `/Homework2/Web/article/${articleId}`;
    });
}
