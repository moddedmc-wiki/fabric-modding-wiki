export function Download({ type, href }) {
  return (
    <>
      <div align="center">
        <a
          target="_blank"
          className="text-cyan-700 hover:text-cyan-800 dark:text-amber-600 dark:hover:text-amber-700"
          href={href}
        >
          Download {type}
        </a>
      </div>
      <br />
    </>
  );
}

export function Subtitle({ children }) {
  return (
    <>
      <div align="center">
        <small>{children}</small>
      </div>
      <br />
    </>
  );
}
