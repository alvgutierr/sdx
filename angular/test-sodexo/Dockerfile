FROM node:20 as builder
RUN mkdir -p /app
WORKDIR /app
COPY package*.json /app
#COPY . .
RUN npm install
COPY . /app
RUN npm run build --prod
#--omit=dev

FROM nginx:alpine
COPY --from=builder /app/dist/* /usr/share/nginx/html
#EXPOSE 80
#Start the nginx web server
#CMD ["nginx", "-g", "daemon off;"]

#run with
#docker build -t web-fav .
#docker run -d --name web-favorites -it -p 81:80 web-fav
