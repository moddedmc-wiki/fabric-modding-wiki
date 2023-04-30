import { serverQueryContent } from '#content/server'
import { SitemapStream, streamToPromise } from 'sitemap'

export default defineEventHandler(async (event) => {
  // Fetch all documents
  const docs = await serverQueryContent(event).find()
  const sitemap = new SitemapStream({
    hostname: 'https://fabric.mineblock11.dev/'
  })

  const archived = ["1.19.3"]

  for (const doc of docs) {
    let arch = false;

    archived.forEach(vers => {
      if(doc._path?.includes(vers)) arch = true;
    })

    if(arch) continue;

    if(doc._file?.endsWith(".yml")) continue;

    console.log(doc._file)

    sitemap.write({
      url: doc._path,
      changefreq: 'monthly'
    });
  }
  sitemap.end()

  return streamToPromise(sitemap)
})