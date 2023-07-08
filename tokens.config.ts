import { defineTheme } from 'pinceau'

export default defineTheme({
  color: {
    black: '#121212',
    gray: {
      50: "#FAFAFA",
      100: "#F5F5F5",
      200: "#EEEEEE",
      300: "#E0E0E0",
      400: "#BDBDBD",
      500: "#9E9E9E",
      600: "#757575",
      700: "#616161",
      800: "#424242",
      900: "#212121"
    },
    primary: {
      50: "#FBE9E7",
      100: "#FFCCBC",
      200: "#FFAB91",
      300: "#FF8A65",
      400: "#FF7043",
      500: "#FF5722",
      600: "#F4511E",
      700: "#E64A19",
      800: "#D84315",
      900: "#BF360C"
    }
  },

  font: {
    sans: "apple-system,BlinkMacSystemFont,\"Segoe UI\",Roboto,Helvetica,Arial,sans-serif,\"Apple Color Emoji\",\"Segoe UI Emoji\",\"Segoe UI Symbol\"",
    mono: "monospace"
  },

  docus: {
    loadingBar: {
      gradientColorStop1: "#dc2c00",
      gradientColorStop2: "#fec134",
      gradientColorStop3: "#a9e100"
    }
  }
})