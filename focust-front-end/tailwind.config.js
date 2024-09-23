/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/components/**/*.{ts,tsx}',
    './src/pages/*.{html,ts,tsx}',
    './src/*.{ts,tsx}',
    './public/*.{html}',
    './*.{ts,tsx}'
  ],
  theme: {
    extend: {},
  },
  variants: {},
  plugins: [],
}

