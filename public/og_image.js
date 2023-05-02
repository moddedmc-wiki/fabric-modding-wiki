window.onload = () => {
    setTimeout(() => {
        const title = document.querySelector('meta[property="og:title"]').content;
        const desc = document.querySelector('meta[name="description"]').content;

        document.head.innerHTML += `<meta property="og:image" content="https://og.mineblock11.dev/fabric-community-wiki?title=${encodeURIComponent(title)}&description=${encodeURIComponent(desc)}&path=${window.location.pathname}" />`
    }, 200);
}