import { Config } from "./src/config";

export const defaultConfig: Config = {
  url: "https://www.imdb.com/",
  match: "https://www.imdb.com/title/**",
  maxPagesToCrawl: 50,
  outputFileName: "output.json",
  maxTokens: 2000000,
};
