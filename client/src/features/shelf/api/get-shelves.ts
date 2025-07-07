import { axiosInstance } from "@/lib/api-client"
import { Shelf } from "@/types/api"
import { queryOptions, useQuery } from "@tanstack/react-query"
import { AxiosError } from "axios"

export async function getShelves(): Promise<Shelf[]> {
  const response = await axiosInstance.get<Shelf[]>("/api/v1/shelves")
  return response.data
}

export const getShelvesQueryOptions = () => {
  return queryOptions<Shelf[], AxiosError>({
    queryKey: ["shelves"],
    queryFn: () => getShelves(),
  })
}

export const useShelves = () => {
  return useQuery({
    ...getShelvesQueryOptions(),
  })
}
