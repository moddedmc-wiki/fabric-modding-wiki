import { Cards, Card } from "nextra/components";

export function Contributor({ username }) {
  return (
    <>
      <a href={`https://github.com/${username}`} target="_blank" class="transform
        transition duration-100 hover:scale-100 scale-75">
        <img
          class="w-20 h-20 m-0 p-0 rounded pointer-events-none"
          src={"https://github.com/" + username + ".png"}
        />
      </a>
    </>
  );
}

export function Contributors({ list }) {
  return (
    <span class="flex flex-row flex-wrap gap-4 pt-5">
      {list.map((username) => {
        return <Contributor username={username}/>;
      })}
    </span>
  );
}
